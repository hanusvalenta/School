import Db.Database;
import Db.Query;

import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    IO.println(String.format("Hello and welcome!"));

    try {
        Database database = new Database("test", "admin", "");
        Object[] data = {"6", "adam", "nemec", "lul", "lol"};

        IO.println();
        int hotovo = database.insert("users:", data);
    }catch (SQLException e) {
        System.out.println(e.getMessage());
    }
}