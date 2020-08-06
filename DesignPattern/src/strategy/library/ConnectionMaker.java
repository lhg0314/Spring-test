package strategy.library;

import java.sql.Connection;

public interface ConnectionMaker {

	Connection getConnection();

}
