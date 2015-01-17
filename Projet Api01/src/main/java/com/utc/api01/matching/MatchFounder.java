package com.utc.api01.matching;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.utc.api01.model.Book;
import com.utc.api01.model.Evaluation;
import com.utc.api01.model.Notes;
import com.utc.api01.model.Question;

public class MatchFounder {
    
    private ArrayList<Book> bookList;
    private ArrayList<Question> questionList;
    private ArrayList<Notes> noteList;
    private ArrayList<Evaluation> evaluationList;
    
    ArrayList<Question> hightQuestion;
    ArrayList<Question> mediumQuestion;
    ArrayList<Question> lowQuestion;
    
    ArrayList<Book> hightBook;
    ArrayList<Book> mediumBook;
    ArrayList<Book> lowBook;
    
    String bestType;
    String bestAutor;
    
    private Map<Integer,ArrayList<Notes>> mapEval;
    Map<Question,Float> prctQuestion;
    
    private static final int HIGHT_RANGE = 4;
    private static final int MEDIUM_RANGE = 2;
    private static final int LOW_RANGE = 0;
    
    /**
     * @param bookList
     * @param questionList
     * @param noteList
     * @param evaluationList
     */
    public MatchFounder(ArrayList<Book> bookList,
            ArrayList<Question> questionList, ArrayList<Notes> noteList,
            ArrayList<Evaluation> evaluationList) {
        super();
        this.bookList = bookList;
        this.questionList = questionList;
        this.noteList = noteList;
        evaluationList = evaluationList;
    }
    /**
     * @return the bookList
     */
    public ArrayList<Book> getBookList() {
        return bookList;
    }
    /**
     * @param bookList the bookList to set
     */
    public void setBookList(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }
    /**
     * @return the questionList
     */
    public ArrayList<Question> getQuestionList() {
        return questionList;
    }
    /**
     * @param questionList the questionList to set
     */
    public void setQuestionList(ArrayList<Question> questionList) {
        this.questionList = questionList;
    }
    /**
     * @return the noteList
     */
    public ArrayList<Notes> getNoteList() {
        return noteList;
    }
    /**
     * @param noteList the noteList to set
     */
    public void setNoteList(ArrayList<Notes> noteList) {
        this.noteList = noteList;
    }
    /**
     * @return the evaluationList
     */
    public ArrayList<Evaluation> getEvaluationList() {
        return evaluationList;
    }
    /**
     * @param evaluationList the evaluationList to set
     */
    public void setEvaluationList(ArrayList<Evaluation> evaluationList) {
        evaluationList = evaluationList;
    }
    
    private void setMapEvalAndQuestionRank(){
        
        mapEval = new HashMap<Integer,ArrayList<Notes>>();
        hightQuestion = new ArrayList<Question>();
        mediumQuestion = new ArrayList<Question>();
        lowQuestion = new ArrayList<Question>();
        
        
        ArrayList<Notes> lowRange = new ArrayList<Notes>();
        ArrayList<Notes> mediumRange = new ArrayList<Notes>();
        ArrayList<Notes> hightRange = new ArrayList<Notes>();
        
        for(Notes note : noteList){
            if(note.getNote() >= HIGHT_RANGE){
                if(!hightRange.contains(note.getEvaluation()))
                    hightRange.add(note);
                hightQuestion.add(note.getQuestion());
            }
            else if(note.getNote() >= MEDIUM_RANGE){
                if(!mediumRange.contains(note.getEvaluation()))
                    mediumRange.add(note);   
                mediumQuestion.add(note.getQuestion());
            }
            else{
                if(!lowRange.contains(note.getEvaluation()))
                    lowRange.add(note);
                lowQuestion.add(note.getQuestion());
            }
        }
        
        mapEval.put(HIGHT_RANGE, hightRange);
        mapEval.put(MEDIUM_RANGE, hightRange);
        mapEval.put(LOW_RANGE, hightRange);
        
    }
    
    private void setQuestionPrct(){
        
        prctQuestion = new HashMap<Question, Float>();
        
        for(Question q : questionList){
            prctQuestion.put(q, (float) 0);
        }
        
        for(Question q : questionList){
            for(Question aq : hightQuestion){
                if(aq.getIdQuestions() == q.getIdQuestions()){
                    float prct = prctQuestion.get(q);
                    prctQuestion.put(q,prct+1);
                    //hightQuestion.remove(aq);
                }
            }
        }
    }
    
    private void setInterest(){
        Map<String,Float> auteurPrct = new HashMap<String, Float>();
        Map<String,Float> typePrct = new HashMap<String, Float>();
        
        //On récupére la question qui a reçu la meilleur note le plus grand nombre de fois
        //Cette question représente un interet porté par le lecteur
        
        Question bestQuestion = null;
        Float tmpMaxPrct = (float) -3000;
        
        for(Question q : questionList){
            float resPrct = prctQuestion.get(q);
            if(resPrct > tmpMaxPrct){
                bestQuestion = q;
                tmpMaxPrct = resPrct;
            }
        }
        
        //Ici best question est la question ayant le plus souvent reçu une bonne note
        //On récupére la liste des livres concernant cette question
        
        ArrayList<Book> bookForBestQuestion = new ArrayList<Book>();
        for(Notes n : noteList){
            if(n.getQuestion().getIdQuestions() == bestQuestion.getIdQuestions()){
                bookForBestQuestion.add(n.getEvaluation().getBook());
            }
        }
        
        ArrayList<String> auteurList = new ArrayList<String>();
        for(Book book : bookForBestQuestion){
            if(auteurPrct.containsKey(book.getAutor())){
                Float tmp = auteurPrct.get(book.getAutor());
                auteurPrct.put(book.getAutor(), tmp+1);
            }else{
                auteurList.add(book.getAutor());
                auteurPrct.put(book.getAutor(), (float)1);
            }
        }
        
        ArrayList<String> typeList = new ArrayList<String>();
        for(Book book : bookForBestQuestion){
            if(typePrct.containsKey(book.getType())){
                Float tmp = typePrct.get(book.getType());
                typePrct.put(book.getType(), tmp+1);
            }else{
                typeList.add(book.getAutor());
                typePrct.put(book.getType(), (float)1);
            }
        }
        
        //On récupére le meilleur Auteur et le meilleur Type
        bestAutor = "";
        Float tmpMaxAutor = (float) -3000;
        bestType = "";
        Float tmpMaxType = (float) -3000;
        
        for(String autor:auteurList){
            float a = auteurPrct.get(autor);
            if(a>tmpMaxAutor){
                bestAutor = autor;
                tmpMaxAutor = a;
            }
        }
        
        for(String type:auteurList){
            float a = auteurPrct.get(type);
            if(a>tmpMaxType){
                bestAutor = type;
                tmpMaxType = a;
            }
        }
        
        //Ici on posséde l'auteur et le type le plus présent lorsque l'utilisateur 
        //a mis une bonne note a cette question max
        
    }
    
    private Book foundBook(String autor){
        
        boolean readed = false;
        
        for(Book b : bookList){
            if(b.getAutor().equals(autor)){
                for(Notes n : noteList){
                    if(n.getEvaluation().getBook().getIdBook() == b.getIdBook()){
                        readed = true;
                        break;
                    }
                }
                if(!readed){
                    return b;
                } readed = false;
            }
        }
        
        return bookList.get(0);
    }
    
    public Book matchFounding(){
       
        //On repartie les notes et les questions en categories
        setMapEvalAndQuestionRank();
        
        //On calcule le nombre de fois qu'une réponse à une question est bonne lorsque
        //Un livre à reçu une bonne note
        //Lorsque X met une bonne note dans Y pourcent des cas c'est a la question Q
        setQuestionPrct();
        
        //Lorsque X à répondu d'une bonne note a cette question l'auteur etait A dans Y pourcent des cas
        setInterest();
        
        //Pour le moment on cherche seulement un livre de même auteur
        //Lorsqu'il y a apprentissage il faut faire une proposition auteur/type
        //Ensuite attendre l'evaluation et déduire une influence préçise
        Book conseille = foundBook(bestAutor);
        
        return conseille;
    }

}
