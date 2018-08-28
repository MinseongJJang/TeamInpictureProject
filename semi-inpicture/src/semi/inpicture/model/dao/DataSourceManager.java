package semi.inpicture.model.dao;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class DataSourceManager {
	private static DataSourceManager instance ;
	private DataSource dataSource;
	private String driver = "oracle.jdbc.OracleDriver";
	private DataSourceManager() {
		BasicDataSource dbcp = new BasicDataSource();
		dbcp.setDriverClassName(driver);
		dbcp.setUrl("jdbc:oracle:thin:@127.0.0.1:1521:xe");
		dbcp.setUsername("inpicture");
		dbcp.setPassword("inpicture");
		//각종 dbcp 설정
		//커넥션 풀의 최대 커넥션 수의 기본은 8이다. -> 20으로 변경해본다.
		dbcp.setMaxTotal(20);
		this.dataSource = dbcp;
	}
	
	public static DataSourceManager getInstance() {
		if(instance==null)
			instance = new DataSourceManager();
		return instance;
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}
}
