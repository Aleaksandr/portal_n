package bl.simple;

import util.DaoFactory;
import org.apache.log4j.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by hirs akeaksandr on 25.04.15.
 * Class for return relevant sql dao factory
 */

class DaoFactoryProvider {

	private static Logger logger = Logger.getLogger(DaoFactoryProvider.class);

	private static DaoFactory daoFactory;

	static {
		String filename = "config.properties";
		ClassLoader classLoader = DaoFactoryProvider.class.getClassLoader();
		InputStream input = classLoader.getResourceAsStream(filename);
		Properties properties = new Properties();
		try {
			properties.load(input);
			String factoryClassName = properties.getProperty("dao_factory_class");
			logger.info("DaoFactory :"+ factoryClassName);
			Class factoryClass = classLoader.loadClass(factoryClassName);
			daoFactory = (DaoFactory) factoryClass.newInstance();
		} catch (ClassNotFoundException e) {
			logger.fatal("Failed to load dao factory class", e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			logger.fatal("Failed to load " + filename, e);
			throw new RuntimeException(e);
		} catch (InstantiationException | IllegalAccessException e) {
			logger.fatal("Failed to create an instance of a dao factory class",
					e);
			throw new RuntimeException(e);
		}
	}

	static DaoFactory getFactory() {
		return daoFactory;
	}
}
