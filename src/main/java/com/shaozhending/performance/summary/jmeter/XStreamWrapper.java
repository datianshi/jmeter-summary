package com.shaozhending.performance.summary.jmeter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Properties;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.reflection.ReflectionProvider;
import com.thoughtworks.xstream.mapper.Mapper;
import com.thoughtworks.xstream.mapper.MapperWrapper;

/**
 * XStream register all the necessary converters from mapping.properties
 * 
 * @author Shaozhen Ding
 *
 */

public final class XStreamWrapper extends XStream {
	
	private final Properties aliasToClass = new Properties();
	private final Properties classToAlias = new Properties();
	

	public XStreamWrapper(ReflectionProvider reflectionProvider) throws Exception{
		super(reflectionProvider);
		initProperties();

	}

	private void initProperties() throws FileNotFoundException, IOException, IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
		Properties p = new Properties();

		p.load(this.getClass().getClassLoader()
		        .getResourceAsStream("META-INF/mapper.properties"));
		for (Map.Entry<Object, Object> m : p.entrySet()){
			String key = (String) m.getKey();
			String value = (String) m.getValue();
			if(!key.startsWith("_")){
				aliasToClass.put(key, value);
				classToAlias.put(value, key);
			}
			else{
				key = key.substring(1);
                final String trimmedValue = value.trim();
                if (trimmedValue.equals("collection") // $NON-NLS-1$
                 || trimmedValue.equals("mapping")) { // $NON-NLS-1$
                    registerConverter(key, this, true);
                    registerConverter(key, this, true);
                } else {
                    registerConverter(key, this, false);
                    registerConverter(key, this, false);
                }
			}
		}
	}

	@Override
	protected MapperWrapper wrapMapper(MapperWrapper next) {
		return new MapperWrapper(next) {
			@Override
			public Class<?> realClass(String alias) {
				String fullName = aliasToClass(alias);
				return super.realClass(fullName == null ? alias : fullName);
			}

			@Override
			public String serializedClass(@SuppressWarnings("rawtypes") Class type) {
				if (type == null) {
					return super.serializedClass(null);
				}
				String alias = classToAlias(type.getName());
				return alias == null ? super.serializedClass(type) : alias;
			}
		};
	}

	private String aliasToClass(String s) {
		String r = aliasToClass.getProperty(s);
		return r == null ? s : r;
	}

	private String classToAlias(String s) {
		String r = classToAlias.getProperty(s);
		return r == null ? s : r;
	}
	
    private  void registerConverter(String key, XStream jmxsaver, boolean useMapper) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException{
        if (useMapper){
            jmxsaver.registerConverter((Converter) Class.forName(key).getConstructor(
                    new Class[] { Mapper.class }).newInstance(
                            new Object[] { jmxsaver.getMapper() }));
        } else {
            jmxsaver.registerConverter((Converter) Class.forName(key).newInstance());
        }
    }
}
