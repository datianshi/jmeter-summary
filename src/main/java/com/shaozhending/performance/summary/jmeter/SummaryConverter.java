package com.shaozhending.performance.summary.jmeter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.util.Calculator;

import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.converters.collections.AbstractCollectionConverter;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.mapper.Mapper;
/**
 * Xstream converter to scan jtl file and inserting the result files
 * 
 * @author Shaozhen Ding
 *
 */
public class SummaryConverter extends AbstractCollectionConverter{
	
    private static final Properties aliasToClass = new Properties();
    
	public SummaryConverter(Mapper mapper) {
		super(mapper);
	}

	@Override
	public boolean canConvert(Class type) {
		return type.equals(SummaryResultWrapper.class);
	}

	@Override
	public void marshal(Object source, HierarchicalStreamWriter writer,
			MarshallingContext context) {
		
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader,
			UnmarshallingContext context) {
        SummaryResultWrapper results = new SummaryResultWrapper();
        Collection<SampleResult> samples = new ArrayList<SampleResult>();
        String ver = reader.getAttribute("version");  //$NON-NLS-1$
        if (ver == null || ver.length() == 0) {
            ver = "1.0";  //$NON-NLS-1$
        }
        results.setVersion(ver);
        Calculator totalRow = new Calculator("Total");
        while (reader.hasMoreChildren()) {
            reader.moveDown();
            SampleResult sample = (SampleResult) readItem(reader, context, results);
            Calculator row = results.getRow(sample.getSampleLabel());
            if(row == null){
            	row = new Calculator(sample.getSampleLabel());
            	results.putRow(sample.getSampleLabel(), row);
            }
            row.addSample(sample);
            totalRow.addSample(sample);
            samples.add(sample);
            reader.moveUp();
        }
        results.putRow("Total", totalRow);
        results.setSampleResults(samples);
        return results;		
	}
}
