package Db;

public class Query {
    private StringBuilder query = new StringBuilder();

    public Query delete(String table) {
        query = new StringBuilder();
        query.append("DELETE FROM ");
        query.append(table);
        return this;
    }

    public Query where(String condition) {
        if (query.toString().contains(" WHERE ")) {
            query.append(" AND ");
        } else {
            query.append(" WHERE ");
        }
        query.append(condition);
        return this;
    }

    public Query update(String table){
        query = new StringBuilder();
        query.append("UPDATE ");
        query.append(table);
        query.append(" SET ");
        return this;
    }

    public Query set(String[] columns){
        int count = columns.length;
        if (count == 0){
            throw new RuntimeException("No columns provided for update");
        }
        for (String column : columns){
            query.append(column);
            query.append("=");
            query.append("?");
            query.append(",");
        }
        query.deleteCharAt(query.lastIndexOf(","));
        return this;
    }

    public Query columns(String[] columns) {
        if (columns != null && columns.length > 0) {
            query.append(" (");
            for (String column : columns) {
                query.append(column).append(",");
            }
            query.deleteCharAt(query.lastIndexOf(","));
            query.append(")");
        }
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
            throw new RuntimeException("No values provided for insert");
        }
        for (int i = 0; i < count; i++){
            query.append("?,");
        }
        query.deleteCharAt(query.lastIndexOf(","));
        query.append(")");
        return this;
    }

    public Query select(String[] columns) {
        query = new StringBuilder();
        query.append("SELECT ");
        if(columns != null && columns.length > 0){
            for (String column : columns) {
                query.append(column);
                query.append(",");
            }
            query.deleteCharAt(query.lastIndexOf(","));
            query.append(" ");
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

    public Query orderBy(String column) {
        query.append(" ORDER BY ");
        query.append(column);
        return this;
    }

    public Query limit(int limit) {
        query.append(" LIMIT ");
        query.append(limit);
        return this;
    }

    public String getQuery(){
        return query.toString();
    }
}
