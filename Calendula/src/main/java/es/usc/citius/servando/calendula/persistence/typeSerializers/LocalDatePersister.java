package es.usc.citius.servando.calendula.persistence.typeSerializers;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.field.types.BaseDataType;
import com.j256.ormlite.support.DatabaseResults;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

import java.sql.SQLException;

/**
 * Created by joseangel.pineiro on 10/9/14.
 */
public class LocalDatePersister extends BaseDataType {

    String format = "yyyy/MM/dd";

    public LocalDatePersister() {
        super(SqlType.STRING, new Class<?>[]{LocalDate.class});
    }

    @Override
    public Object parseDefaultString(FieldType fieldType, String defaultStr) throws SQLException {
        return defaultStr;
    }

    @Override
    public Object resultToSqlArg(FieldType fieldType, DatabaseResults results, int columnPos) throws SQLException {
        return results.getString(columnPos);
    }

    @Override
    public Object sqlArgToJava(FieldType fieldType, Object sqlArg, int columnPos) throws SQLException {
        return DateTimeFormat.forPattern(format).parseLocalDate((String) sqlArg);
    }

    @Override
    public Object javaToSqlArg(FieldType fieldType, Object javaObject) throws SQLException {
        return ((LocalDate) javaObject).toString(format);
    }


    private static final LocalDatePersister singleton = new LocalDatePersister();

    public static LocalDatePersister getSingleton() {
        return singleton;
    }
}
