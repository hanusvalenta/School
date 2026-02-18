import Db.Database;
import Db.Query;

import java.sql.ResultSet;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    IO.println(String.format("Vitej v db editoru"));

    try {
        Database database = new Database("test", "admin", "");
        int hotovo = 0;
        // id	jmeno	prijmeni	cislo	adresa
        IO.println("Zadej moznost \n");
        IO.println("1. pridat 2. upravit 3. Smazat 4. Vypsat \n");
        int option = Integer.parseInt(IO.readln());

        String id = null;
        String jmeno = null;
        String prijmeni = null;
        String cislo = null;
        String adresa = null;

        Object[] data = {};

        switch (option){
            case 1:
                IO.println("Zadej id \n");
                id = IO.readln();
                IO.println("Zadej jmeno \n");
                jmeno = IO.readln();
                IO.println("Zadej prijmeni \n");
                prijmeni = IO.readln();
                IO.println("Zadej cislo \n");
                cislo = IO.readln();
                IO.println("Zadej adresu \n");
                adresa = IO.readln();

                data = new Object[]{id, jmeno, prijmeni, cislo, adresa};
                hotovo = database.insert("users", data);
                IO.println("Pridano \n");
                break;
            case 2:
                IO.println("Zadej co ipravit\n");
                IO.println("1. jmeno 2. prijmeni 3. cislo 4. adresu\n");
                int option2 = Integer.parseInt(IO.readln());
                IO.println("Zadej id co upravit \n");
                id = IO.readln();

                String[] collumss = null;

                switch (option2){
                    case 1:
                        IO.println("Zadej nove jmeno\n");
                        jmeno = IO.readln();
                        collumss = new String[]{"jmeno"};
                        data = new Object[]{jmeno, id};
                        break;
                    case 2:
                        IO.println("Zadej nove prijmeni\n");
                        prijmeni = IO.readln();
                        collumss = new String[]{"prijmeni"};
                        data = new Object[]{prijmeni, id};
                        break;
                    case 3:
                        IO.println("Zadej nove cislo\n");
                        cislo = IO.readln();
                        collumss = new String[]{"cislo"};
                        data = new Object[]{cislo, id};
                        break;
                    case 4:
                        IO.println("Zadej nove adresa\n");
                        adresa = IO.readln();
                        collumss = new String[]{"adresa"};
                        data = new Object[]{adresa, id};
                        break;
                    default:
                        IO.println("neplatna moznost \n");
                        break;
                }

                hotovo = database.update("users", collumss, "id = ?", data);
                IO.println("Upraveno \n");
                break;
            case 3:
                IO.println("Zadej id na smazani\n");
                id = IO.readln();
                data = new Object[]{id};
                hotovo = database.delete("users", "id = ?", data);
                IO.println("Smazano \n");
                break;
            case 4:
                ResultSet rs = database.select("users", null, null, null);
                while (rs.next()) {
                    IO.println(rs.getString("id") + "\t" +
                            rs.getString("jmeno") + "\t" +
                            rs.getString("prijmeni") + "\t" +
                            rs.getString("cislo") + "\t" +
                            rs.getString("adresa") + "\n");
                }
                break;
            default:
                if (hotovo == 0){
                    IO.println("neplatna moznost \n");
                }
                break;
        }

        IO.println(hotovo);
    }catch (SQLException e) {
        System.out.println(e.getMessage());
    }
}