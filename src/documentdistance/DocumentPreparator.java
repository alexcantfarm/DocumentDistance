/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package documentdistance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author deb
 */
public class DocumentPreparator{
    
    public HashMap<String, Integer> document1;
    public HashMap<String, Integer> document2;
    
    public int[] document1Count;
    public int[] document2Count;
    
    
    DocumentPreparator(String text1, String text2){
        makeDocuments(text1, text2);
        
    }
    
    
    private HashMap<String,Integer> frequencies(String[] text){
        
        HashMap<String,Integer> result = new HashMap<>();
       
        for(String s : text){
            if(s.length() > 1){
                if(!result.containsKey(s)){
                    result.put(s,1);
                }else{
                    int count = result.get(s) + 1;
                    result.replace(s, count);
                }
            }
        }
        
        return result;
    }
    
    private void makeDocuments(String text1, String text2){
        
        //better regexes
        String delimiters = "[\t\r\n \\*.,_+-/{}()\"/=!?\\]\\[=|<>%&#@';:^]+";
        String[] wordArray1 = text1.split(delimiters);
        String[] wordArray2 = text2.split(delimiters);
        
        
        String[] corpus = new String[wordArray1.length + wordArray2.length];
        
        System.arraycopy(wordArray1, 0, corpus, 0, wordArray1.length);
        System.arraycopy(wordArray2, 0, corpus, wordArray1.length ,wordArray2.length);
        
        //remove dublicates
        Set<String> corpusSet = new HashSet<String>();
        
        for(int i = 0; i < corpus.length; i++){
            corpus[i] = corpus[i].toLowerCase();
            if((corpus[i].length() > 3) && (corpusSet.add(corpus[i]))){
                System.out.println("added: " + corpus[i]);
            }else{
                System.out.println("not added: " + corpus[i]);
            }
            
        }
        
        corpus = corpusSet.toArray(new String[corpusSet.size()]);
        
        for(String s : corpus){
            System.out.println("debug: " + s);
        }
        
        Arrays.sort(corpus);
        
        //make integer array for each doc where each element from 0 to n correspond to 
        //corpuses words from 0 to n giving a number of times that word appears in a document
        
        ArrayList<String> mainCorpusList = new ArrayList<>(Arrays.asList(corpus));
        
        
        HashMap<String, Integer> wordsCountMap1 = frequencies(wordArray1);
        HashMap<String, Integer> wordsCountMap2 = frequencies(wordArray2);
        
       
        //make text1 and text2 into <String, int> maps, where both have all the 
        //words in corpus, with count for each, zero incase its not in the 
        //text
        
        document1 = finalDocument(corpus, wordsCountMap1);
        document2 = finalDocument(corpus, wordsCountMap2);
        document1Count = mapWordsToIntegers(corpus, document1);
        document2Count = mapWordsToIntegers(corpus, document2);


    }
    
    private int[] mapWordsToIntegers(String[] corpus,HashMap<String,Integer> doc){
        int[] result = new int[corpus.length];
        
        for(int i = 0; i< corpus.length; i++){
            if(doc.containsKey(corpus[i])){
                result[i] = doc.get(corpus[i]);
            }else{
                result[i] = 0;
            }
        }
        
        return result;
    }
    
    private HashMap<String, Integer> finalDocument(
            String[] corpus, HashMap<String, Integer> docMap){
        
        HashMap<String,Integer> result = new HashMap<>();
        
        for(String s : corpus)
        { 
            if(docMap.containsKey(s)){
                
                result.put(s, (docMap.get(s)));
            
            }else{
                
                result.put(s, 0);
                
            }
           
        }
        
        return result;
        
    }
    
}
