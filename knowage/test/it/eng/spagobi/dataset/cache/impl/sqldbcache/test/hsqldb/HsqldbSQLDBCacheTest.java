/*
 * Knowage, Open Source Business Intelligence suite
 * Copyright (C) 2016 Engineering Ingegneria Informatica S.p.A.
 * 
 * Knowage is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Knowage is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package it.eng.spagobi.dataset.cache.impl.sqldbcache.test.hsqldb;

import org.apache.log4j.Logger;

import it.eng.spagobi.dataset.cache.impl.sqldbcache.test.AbstractSQLDBCacheTest;
import it.eng.spagobi.dataset.cache.test.TestConstants;
import it.eng.spagobi.dataset.cache.test.TestDataSourceFactory;

/**
 * @author Marco Cortella (marco.cortella@eng.it)
 *
 */
public class HsqldbSQLDBCacheTest extends AbstractSQLDBCacheTest {
	
	static private Logger logger = Logger.getLogger(HsqldbSQLDBCacheTest.class);


	//Create Datasources specific for this test
	@Override
	public void createDataSources(){
		dataSourceReading = TestDataSourceFactory.createDataSource(TestConstants.DatabaseType.MYSQL, false);
		dataSourceWriting = TestDataSourceFactory.createDataSource(TestConstants.DatabaseType.HSQLDB, true);
	}
}
