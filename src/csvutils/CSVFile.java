package csvutils;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Map;

import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.UniqueHashCode;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvMapReader;
import org.supercsv.io.ICsvMapReader;
import org.supercsv.prefs.CsvPreference;

/**
 * Exemple : 
 * 
 * CSVFile file = new CSVFile();
		
		try {
			file.read();
		} catch(Exception e) {
			System.out.println("File cannot be read");
		}
		
		System.out.println(file.toString());
 * @author albin
 *
 */


public class CSVFile {
	
	/**
	 * File the read
	 */
	static final String CSV_FILENAME = "data/DC_TC_PC_VC_ACEN_MW_DM_NBP.data.csv";
	
	/**
	 * The ArrayList containing the Maps
	 */
	private ArrayList<Map<String, Object>> objects = null;
	
	/**
	 * Init the mapReader if possible
	 */
	public CSVFile() {
		objects = new ArrayList<Map<String, Object>>();
	}
	
	
	/**
	 * Sets up the processors used to get the data. 
	 * There are 9 CSV columns, so 9 processors are defined. 
	 * 
	 * Col 1 : CASRN (string)
	 * Col 2 : DC (float)
	 * Col 3 : TC (int)
	 * Col 4 : PC (float)
	 * Col 5 : VC (int)
	 * Col 6 : ACEN (float)
	 * Col 7 : MW (float)
	 * Col 8 : DM (float)
	 * Col 9 : NBP (float)
	 * 
	 * @return the cell processors
	 */
	private static CellProcessor[] getProcessors() {
	        
	        // 8 colonnes	        
	        final CellProcessor[] processors = new CellProcessor[] { 
	                new UniqueHashCode(), // CASRN cell No (must be unique)
	                new ParseDouble(), // DC (float)
	                new ParseInt(), // TC (int)
	                new ParseDouble(), // PC (float)
	                new ParseInt(), // VC (int)
	                new ParseDouble(), // ACEN (float)
	                new ParseDouble(), // MW (float)
	                new ParseDouble(), // DM (float)
	                new ParseDouble(), // NBP (float)
	        };
	        
	        return processors;
	}
	
	/**
	 * Read and parse the file
	 */
	public void read() throws Exception {
	        
	        ICsvMapReader mapReader = null;
	        try {
	                mapReader = new CsvMapReader(new FileReader(CSV_FILENAME), CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);
	                
	                // the header columns are used as the keys to the Map
	                final String[] header = mapReader.getHeader(true);
	                final CellProcessor[] processors = getProcessors();
	                
	                Map<String, Object> customerMap;
	                while( (customerMap = mapReader.read(header, processors)) != null ) {
	                        /*System.out.println(String.format("lineNo=%s, rowNo=%s, customerMap=%s", mapReader.getLineNumber(),
	                                mapReader.getRowNumber(), customerMap));*/
	                        objects.add(customerMap);
	                }
	                
	        }
	        finally {
	                if( mapReader != null ) {
	                        mapReader.close();
	                }
	        }
	}
	
	/**
	 * Return all the data
	 * 
	 * @return
	 */
	public ArrayList<Map<String, Object>> getMaps() {
		return objects;
	}
	
	public String toString() {
		
		String str = "";
		if(objects.size() > 0) {
			for (Map<String, Object> map : objects) {
				str += map + "\n";
			}
		} else {
			str = "There is not readed data";
		}
		
		return str;
	}

}
