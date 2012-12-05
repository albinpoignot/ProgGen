package gep_dc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class GepData {
	private double data[][]; 
	private ArrayList<String> substance_list;
	private double DC[];
	private ArrayList<String> varNames;

	public GepData(String FileName) {
		
		String tmp;
		substance_list = new ArrayList<String>();
		try {
	        BufferedReader br = new BufferedReader(new FileReader(FileName));

	        //lire la premiere ligne
	        tmp = br.readLine();
	        String[] split = tmp.split(";");
	        varNames = new ArrayList<String>();
	        
	        // Stockage des noms de variables
	        for(int i=2; i<split.length; i++) {
	        	varNames.add(split[i]);
	        }

	        
	        //lire toute les ligne pour enregistrer les nom des substances 
	        while ( (tmp=br.readLine())!=null ) {
				String[] splitted = tmp.split(";");
				substance_list.add(splitted[0]);
			}
	        br.close();
	        //ensuite lire les valeurs de DC et des autres données
	        br = new BufferedReader(new FileReader(FileName));
	        //lire la premiere ligne
	        br.readLine();
	        DC = new double[substance_list.size()];
	        data = new double[substance_list.size()][7];
	        int subs = 0;
	        while ( (tmp=br.readLine())!=null ) {
				String[] splitted = tmp.split(";");
				DC[subs] = Double.parseDouble(splitted[1]);
				for (int i=2;i<splitted.length;i++) {
					data[subs][i-2] = Double.parseDouble(splitted[i]);
				}
				subs ++;
			}
           br.close();
	    }
	    catch (Exception e){
	    		e.printStackTrace();
	    }
	}
	
	public double getData(int substance, int variable){
		return data[substance][variable];
	}
	
	public double getDC(int substance) {
		return DC[substance];
	}
	
	public String toString() {
		String S = "";
		for (int i = 0; i<substance_list.size() ; i++ ) {
			S += substance_list.get(i)+":";
			S+= "DC="+String.valueOf(DC[i]);
			for (int j=0;j<7;j++) {
				S += ", x"+String.valueOf(j)+"="+String.valueOf(data[i][j]);
			}
			S += "\n";
		}
		return S;
	}
	
	public ArrayList<String> getVarNames() {
		return varNames;
	}
	
	public int getNbSubstances() {
		return data.length;
	}
	
	public int getNbVars() {
		return varNames.size();
	}
	
	public ArrayList<String> getSubstanceList() {
		return substance_list;
	}

}
