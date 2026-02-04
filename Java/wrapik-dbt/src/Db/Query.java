package Db;

public class Query {
    private StringBuilder query;

    public Query delete(String table) {
        query = new StringBuilder();
        query.append("DELETE FROM ");
        query.append(table);
        return this;
    }

    public Query where(String condition) {
        query.append(" WHERE ");
        query.append(condition);
        return this;
    }

    public Query update(String table){
        query.append("UPDATE ");
        query.append(table);
        query.append(" SET ");
        return this;
    }

    public Query set(String [] collums){
        int count = collums.length;
        if (count == 0){
            throw new RuntimeException("fuck");
        }
        for (String column : collums){
            query.append(column);
            query.append("=");
            query.append("?");
            query.append(",");
        }
        query.deleteCharAt(query.lastIndexOf(","));
        return this;
    }

    public Query insert(String table){
        query = new StringBuilder();
        query.append("INSERT INTO ");
        query.append(table);
        return this;
    }

    public Query values(Object[] params) {
        query.append(" VALUES(");
        int count = params.length;
        if (count == 0){
            throw new RuntimeException("NO");
        }
        for (int i = 0; i < count; i++){
            query.append("?,");
        }
        query.deleteCharAt(query.lastIndexOf(","));
        query.append(")");
        return this;
    }

    public Query select(Object[] collums) {
        query = new StringBuilder();
        query.append("SELECT ");
        if(collums != null){
            for (Object column : collums) {
                query.append(column);
                query.append(",");
            }
            query.deleteCharAt(query.lastIndexOf(","));
        }
        else {
            query.append("* ");
        }
        return this;
    }

    public Query from(String table){
        query.append("FROM ");
        query.append(table);
        return this;
    }

    public String getQuery(){
        return query.toString();
    }
}
