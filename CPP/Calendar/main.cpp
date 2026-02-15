#include <iostream>
#include <vector>
#include <string>
#include <limits>
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
        std::cout << ">> Událost '" << title << "' byla úspěšně vytvořena." << std::endl;
    }

    void viewEvents() {
        std::cout << "\n--- Všechny události ---" << std::endl;
        auto cursor = coll.find({});
        for (auto&& doc : cursor) {
            std::cout << bsoncxx::to_json(doc) << std::endl;
        }
    }

    void searchByTitle(const std::string& query) {
        std::cout << "\n--- Hledání v názvu: '" << query << "' ---" << std::endl;
        auto filter = document{} << "title" << open_document 
            << "$regex" << query << "$options" << "i" << close_document << finalize;
        
        for (auto&& doc : coll.find(filter.view())) {
            std::cout << bsoncxx::to_json(doc) << std::endl;
        }
    }

    void searchByDescription(const std::string& query) {
        std::cout << "\n--- Hledání v popisu: '" << query << "' ---" << std::endl;
        auto filter = document{} << "description" << open_document 
            << "$regex" << query << "$options" << "i" << close_document << finalize;
        
        for (auto&& doc : coll.find(filter.view())) {
            std::cout << bsoncxx::to_json(doc) << std::endl;
        }
    }

    void viewRecentEventsByTime(int minutesAgo) {
        auto now = std::chrono::system_clock::now();
        auto timestampNow = std::chrono::duration_cast<std::chrono::seconds>(now.time_since_epoch()).count();
        auto threshold = timestampNow - (minutesAgo * 60);

        std::cout << "\n--- Události za posledních " << minutesAgo << " minut ---" << std::endl;
        
        auto filter = document{} << "created_at" << open_document 
            << "$gt" << threshold << close_document << finalize;

        for (auto&& doc : coll.find(filter.view())) {
            std::cout << bsoncxx::to_json(doc) << std::endl;
        }
    }

    void viewLatestEvents(int limit) {
        std::cout << "\n--- Posledních " << limit << " přidaných událostí ---" << std::endl;
        
        mongocxx::options::find opts;
        opts.sort(document{} << "created_at" << -1 << finalize);
        opts.limit(limit);

        for (auto&& doc : coll.find({}, opts)) {
            std::cout << bsoncxx::to_json(doc) << std::endl;
        }
    }

    void viewEventTitlesOnly() {
        std::cout << "\n--- Seznam názvů událostí ---" << std::endl;
        
        mongocxx::options::find opts;
        opts.projection(document{} << "title" << 1 << "_id" << 0 << finalize);

        for (auto&& doc : coll.find({}, opts)) {
            std::cout << bsoncxx::to_json(doc) << std::endl;
        }
    }

    void updateEvent(const std::string& old_title, const std::string& new_title) {
        auto result = coll.update_one(
            document{} << "title" << old_title << finalize,
            document{} << "$set" << open_document << "title" << new_title << close_document << finalize
        );
        
        if (result && result->modified_count() > 0) {
            std::cout << ">> Událost aktualizována z '" << old_title << "' na '" << new_title << "'." << std::endl;
        } else {
            std::cout << ">> Událost s názvem '" << old_title << "' nebyla nalezena." << std::endl;
        }
    }

    void deleteEvent(const std::string& title) {
        auto result = coll.delete_one(document{} << "title" << title << finalize);
        if (result && result->deleted_count() > 0) {
            std::cout << ">> Událost '" << title << "' smazána." << std::endl;
        } else {
            std::cout << ">> Událost s názvem '" << title << "' nebyla nalezena." << std::endl;
        }
    }
};

void printMenu() {
    std::cout << "\n=== KALENDÁŘ - MONGODB ===" << std::endl;
    std::cout << "1. Vytvořit novou událost" << std::endl;
    std::cout << "2. Zobrazit všechny události" << std::endl;
    std::cout << "3. Hledat podle názvu (obsahuje text)" << std::endl;
    std::cout << "4. Hledat v popisu (obsahuje text)" << std::endl;
    std::cout << "5. Zobrazit události z posledních X minut" << std::endl;
    std::cout << "6. Zobrazit posledních N přidaných" << std::endl;
    std::cout << "7. Vypsat pouze názvy událostí" << std::endl;
    std::cout << "8. Upravit název události" << std::endl;
    std::cout << "9. Smazat událost" << std::endl;
    std::cout << "0. Ukončit" << std::endl;
    std::cout << "Volba: ";
}

int main() {
    mongocxx::instance inst{}; 

    try {
        CalendarManager calendar("mongodb+srv://hanusvalenta:doot@testclus0.jbxknr2.mongodb.net/?appName=TestClus0");

        int choice;
        std::string input1, input2;
        int val;

        while (true) {
            printMenu();
            if (!(std::cin >> choice)) {
                std::cin.clear();
                std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
                continue;
            }
            std::cin.ignore();

            if (choice == 0) break;

            switch (choice) {
                case 1:
                    std::cout << "Zadejte název: ";
                    std::getline(std::cin, input1);
                    std::cout << "Zadejte popis: ";
                    std::getline(std::cin, input2);
                    calendar.createEvent(input1, input2);
                    break;
                case 2:
                    calendar.viewEvents();
                    break;
                case 3:
                    std::cout << "Hledaný text v názvu: ";
                    std::getline(std::cin, input1);
                    calendar.searchByTitle(input1);
                    break;
                case 4:
                    std::cout << "Hledaný text v popisu: ";
                    std::getline(std::cin, input1);
                    calendar.searchByDescription(input1);
                    break;
                case 5:
                    std::cout << "Počet minut zpětně: ";
                    std::cin >> val;
                    calendar.viewRecentEventsByTime(val);
                    break;
                case 6:
                    std::cout << "Počet záznamů k zobrazení: ";
                    std::cin >> val;
                    calendar.viewLatestEvents(val);
                    break;
                case 7:
                    calendar.viewEventTitlesOnly();
                    break;
                case 8:
                    std::cout << "Starý název: ";
                    std::getline(std::cin, input1);
                    std::cout << "Nový název: ";
                    std::getline(std::cin, input2);
                    calendar.updateEvent(input1, input2);
                    break;
                case 9:
                    std::cout << "Název události ke smazání: ";
                    std::getline(std::cin, input1);
                    calendar.deleteEvent(input1);
                    break;
                default:
                    std::cout << "Neplatná volba." << std::endl;
            }
        }

    } catch (const std::exception& e) {
        std::cerr << "Error: " << e.what() << std::endl;
    }

    return 0;
}