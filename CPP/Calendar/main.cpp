#include <iostream>
#include <vector>
#include <bsoncxx/json.hpp>
#include <bsoncxx/builder/stream/document.hpp>
#include <mongocxx/client.hpp>
#include <mongocxx/instance.hpp>
#include <mongocxx/uri.hpp>
#include <chrono>

using bsoncxx::builder::stream::close_array;
using bsoncxx::builder::stream::close_document;
using bsoncxx::builder::stream::document;
using bsoncxx::builder::stream::finalize;
using bsoncxx::builder::stream::open_array;
using bsoncxx::builder::stream::open_document;

class CalendarManager {
private:
    mongocxx::client conn;
    mongocxx::database db;
    mongocxx::collection coll;

public:
    CalendarManager(const std::string& uri_str) : 
        conn(mongocxx::uri{uri_str}), 
        db(conn["calendar"]), 
        coll(db["events"]) {}

    void createEvent(const std::string& title, const std::string& description) {
        auto now = std::chrono::system_clock::now();
        auto timestamp = std::chrono::duration_cast<std::chrono::seconds>(now.time_since_epoch()).count();

        auto builder = document{};
        bsoncxx::document::value doc_value = builder
            << "title" << title
            << "description" << description
            << "created_at" << timestamp
            << finalize;

        coll.insert_one(doc_value.view());
        std::cout << "Event '" << title << "' created successfully." << std::endl;
    }

    void viewEvents() {
        std::cout << "\n--- Current Calendar Events ---" << std::endl;
        auto cursor = coll.find({});
        for (auto&& doc : cursor) {
            std::cout << bsoncxx::to_json(doc) << std::endl;
        }
    }

    void updateEvent(const std::string& old_title, const std::string& new_title) {
        coll.update_one(
            document{} << "title" << old_title << finalize,
            document{} << "$set" << open_document << "title" << new_title << close_document << finalize
        );
        std::cout << "Event updated from '" << old_title << "' to '" << new_title << "'." << std::endl;
    }

    void deleteEvent(const std::string& title) {
        coll.delete_one(document{} << "title" << title << finalize);
        std::cout << "Event '" << title << "' deleted." << std::endl;
    }
};

int main() {
    mongocxx::instance inst{}; 

    try {
        CalendarManager calendar("mongodb+srv://hanusvalenta:doot@testclus0.jbxknr2.mongodb.net/?appName=TestClus0");

        // 1. Create
        calendar.createEvent("Standup Meeting", "Daily sync with the dev team");
        calendar.createEvent("Lunch Break", "Gym and food");

        // 2. View
        calendar.viewEvents();

        // 3. Update
        calendar.updateEvent("Lunch Break", "Team Lunch");

        // 4. Delete
        calendar.deleteEvent("Standup Meeting");

        // View final state
        calendar.viewEvents();

    } catch (const std::exception& e) {
        std::cerr << "Error: " << e.what() << std::endl;
    }

    return 0;
}