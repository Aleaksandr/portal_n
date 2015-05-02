package dao.mysql;



import dao.dao.BaseIdDao;
import dao.dao.RelationshipStatusDao;

import javax.sql.DataSource;

public class MySqlRelationshipStatusDao extends BaseIdDao<Integer> implements RelationshipStatusDao {

	public MySqlRelationshipStatusDao(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	protected String getTableName() {
		return "`newsportal`.`relationship_statuses`";
	}
	
}
