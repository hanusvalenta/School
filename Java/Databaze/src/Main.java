import java.io.Console;
import java.sql.*;
import java.util.Objects;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SQLException {
        Funkce();
    }

    private static void Funkce() throws SQLException {
        Console console = System.console();
        if (console == null) {
            return;
        }
        String url = "jdbc:mysql://localhost:3306/test"; // Ensure this DB exists
        String user = "admin";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {

        if (connection != null) {
            while (true) {
                console.printf("\nZadej 1. Vypsat 2. Pridat 3. Upravit 4. Smazat 5. Konec\n");
                String p = console.readLine();

                if (Objects.equals(p, "1")) {
                    try (Statement statement = connection.createStatement();
                         ResultSet resultSet = statement.executeQuery("SELECT * FROM `users`")) {
                        while (resultSet.next()) {
                            console.printf("%s \t %s \t %s \t %s\n", resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
                        }
                    }
                } else if (Objects.equals(p, "2")) {
                    String jmeno = console.readLine("Zadej jmeno: ");
                    String prijmeni = console.readLine("Zadej prijmeni: ");
                    String cislo = console.readLine("Zadej cislo: ");
                    String adresa = console.readLine("Zadej adresu: ");
                    String sql = "INSERT INTO users (jmeno, prijmeni, cislo, adresa) VALUES (?, ?, ?, ?)";
                    try (PreparedStatement ps = connection.prepareStatement(sql)) {
                        ps.setString(1, jmeno);
                        ps.setString(2, prijmeni);
                        ps.setString(3, cislo);
                        ps.setString(4, adresa);
                        ps.executeUpdate();
                    }
                } else if (Objects.equals(p, "3")) {

                    console.printf("\nZadej koho chces upravit\n");
                    String jmeno = console.readLine("Zadej jmeno: ");

                    String jmeno2 = null;
                    String prijmeni = null;
                    String cislo = null;
                    String adresa = null;

                    try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM `users` WHERE jmeno = ?")) {
                        ps.setString(1, jmeno);
                        try (ResultSet resultSet = ps.executeQuery()) {
                            if (resultSet.next()) {
                                jmeno2 = resultSet.getString(2);
                                prijmeni = resultSet.getString(3);
                                cislo = resultSet.getString(4);
                                adresa = resultSet.getString(5);
                            }
                        }
                    }

                    if (jmeno2 == null) {
                        console.printf("Uzivatel nenalezen.\n");
                        continue;
                    }

                    console.printf("\nZadej co chces upravit\n");
                    console.printf("\nZadej 1. jmeno 2. prijmeni 3. cislo 4. adresa\n");

                    String b = console.readLine();

                    if (Objects.equals(b, "1")) {
                        jmeno2 = console.readLine("Nove jmeno: ");
                    } else if (Objects.equals(b, "2")) {
                        prijmeni = console.readLine("Nove prijmeni: ");
                    } else if (Objects.equals(b, "3")) {
                        cislo = console.readLine("Nove cislo: ");
                    } else if (Objects.equals(b, "4")) {
                        adresa = console.readLine("Nova adresa: ");
                    }

                    try (PreparedStatement ps = connection.prepareStatement("UPDATE `users` SET jmeno = ?, prijmeni = ?, cislo = ?, adresa = ? WHERE jmeno = ?")) {
                        ps.setString(1, jmeno2);
                        ps.setString(2, prijmeni);
                        ps.setString(3, cislo);
                        ps.setString(4, adresa);
                        ps.setString(5, jmeno);
                        ps.executeUpdate();
                    }
                } else if (Objects.equals(p, "4")) {
                    String jmeno = console.readLine("Zadej jmeno: ");
                    try (PreparedStatement ps = connection.prepareStatement("DELETE FROM `users` WHERE jmeno = ?")) {
                        ps.setString(1, jmeno);
                        ps.executeUpdate();
                    }
                } else if (Objects.equals(p, "5")) {
                    break;
                } else {
                    console.printf("Neznama volba\n");
                }
            }
        }
        else {
            console.printf("Neni pripojeno\n");
        }
    }
}
}