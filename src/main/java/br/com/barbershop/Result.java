package br.com.barbershop;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Result {

    /*
     * Complete the 'searchSuggestions' function below.
     *
     * The function is expected to return a 2D_STRING_ARRAY.
     * The function accepts following parameters:
     *  1. STRING_ARRAY repository
     *  2. STRING customerQuery
     */

    public static List<List<String>> searchSuggestions(List<String> repository, String customerQuery) {
    	ArrayList<List<String>> arr = new ArrayList<List<String>>();
        if(customerQuery.length() > 1) {
        	int loopSize = customerQuery.length() + 1;
        	
        	for(int i = 2; i < loopSize; i++) {
        		String stringToQuery = customerQuery.substring(0, i);
            	List<String> filteredList = repository.stream()
                        .filter(a -> a.contains(stringToQuery))
                        .sorted(Comparator.comparing(s -> s))
                        .collect(Collectors.toList());

                   arr.add(filteredList);
        	}
            

           return arr;
        }

        
        return arr;
    }



}