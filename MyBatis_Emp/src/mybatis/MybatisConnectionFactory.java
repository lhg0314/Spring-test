package mybatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisConnectionFactory {
	
	private static SqlSessionFactory sqlSessionFactory;
	
	static {
		String str="mybatis/mybatis-config.xml";
		
		try {
			Reader reader=Resources.getResourceAsReader(str);
			if(sqlSessionFactory == null) {
				sqlSessionFactory=new SqlSessionFactoryBuilder().build(reader);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private MybatisConnectionFactory() {}
	
	
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

}
