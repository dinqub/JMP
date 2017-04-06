package epam.jmp.muha.database.factory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.sql.DataSource;

public class TableFactory 
{
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void createTable(String tableName, Map<String,String> tableLines, 
			List<String> primaryKeys, String autoincrementcolumn)
	{
		
		StringBuilder sql = new StringBuilder("CREATE TABLE "+ tableName+" (");
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			for (Entry<String, String> tableLine : tableLines.entrySet()) 
			{
				sql.append(" "+tableLine.getKey()+" "+tableLine.getValue());
				if (tableLine.getKey().equals(autoincrementcolumn))
				{
					sql.append(" AUTO_INCREMENT");
				}
				sql.append(" ,");
				
			}			
			Statement statment = conn.createStatement();
			sql.append("PRIMARY KEY (");
			for (int i=0 ; i<primaryKeys.size() ; ++i)
			{				
				if (primaryKeys.size()>1 && i>0) sql.append(",");
				sql.append(""+primaryKeys.get(i)+"");
			}	
			sql.append("));");
			statment.execute(sql.toString());
			statment.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
}
