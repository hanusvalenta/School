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
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "");

        if (connection != null) {
            while (true) {
                console.printf("\nZadej 1. Vypsat 2. Pridat 3. Upravit 4. Smazat 5. Konec\n");
                String p = console.readLine();

                if (Objects.equals(p, "1")) {
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM `kontakty`");
                    while (resultSet.next()) {
                        console.printf("%s \t %s \t %s \t %s\n", resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
                    }
                } else if (Objects.equals(p, "2")) {
                    String jmeno = console.readLine("Zadej jmeno: ");
                    String prijmeni = console.readLine("Zadej prijmeni: ");
                    String cislo = console.readLine("Zadej cislo: ");
                    String adresa = console.readLine("Zadej adresu: ");
                    PreparedStatement ps = connection.prepareStatement("INSERT INTO `kontakty` (jmeno, prijmeni, cislo, adresa) VALUES (?, ?, ?, ?)");
                    ps.setString(1, jmeno);
                    ps.setString(2, prijmeni);
                    ps.setString(3, cislo);
                    ps.setString(4, adresa);
                    ps.executeUpdate();
                } else if (Objects.equals(p, "3")) {
                    String jmeno = console.readLine("Zadej jmeno: ");
                    String jmeno2 = console.readLine("Nove jmeno: ");
                    String prijmeni = console.readLine("Nove prijmeni: ");
                    String cislo = console.readLine("Nove cislo: ");
                    String adresa = console.readLine("Nova adresa: ");
                    PreparedStatement ps = connection.prepareStatement("UPDATE `kontakty` SET jmeno = ?, prijmeni = ?, cislo = ?, adresa = ? WHERE jmeno = ?");
                    ps.setString(1, jmeno2);
                    ps.setString(2, prijmeni);
                    ps.setString(3, cislo);
                    ps.setString(4, adresa);
                    ps.setString(5, jmeno);
                    ps.executeUpdate();
                } else if (Objects.equals(p, "4")) {
                    String jmeno = console.readLine("Zadej jmeno: ");
                    PreparedStatement ps = connection.prepareStatement("DELETE FROM `kontakty` WHERE jmeno = ?");
                    ps.setString(1, jmeno);
                    ps.executeUpdate();
                } else if (Objects.equals(p, "5")) {
                    break;
                } else {
                    console.printf("Neznama volba\n");
                }
            }
        }
        else {
            console.printf("Neni koneckte");
        }
    }
}