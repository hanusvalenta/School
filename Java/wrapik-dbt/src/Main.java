import Db.Query;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    IO.println(String.format("Hello and welcome!"));

    Query query = new Query();

    query.delete("zaci").where("jmeno = ?");
    System.out.println(query.getQuery());
}
