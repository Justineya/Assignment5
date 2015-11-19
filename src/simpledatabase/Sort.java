package simpledatabase;
import java.util.ArrayList;

public class Sort extends Operator{
	
	private ArrayList<Attribute> newAttributeList;
	private String orderPredicate;
	ArrayList<Tuple> tuplesResult;
    int count=0;
	
	public Sort(Operator child, String orderPredicate){
		int i,j=0;
		Attribute a2;
		// i is where to put the tuple. j is the size of the tuple list.
		ArrayList<Tuple> tuples= new ArrayList<Tuple>(); 
		this.child = child;
		this.orderPredicate = orderPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuplesResult = new ArrayList<Tuple>();
		while(true){
			Tuple tuple=child.next();
			if(tuple==null)
				break;
			tuples.add(tuple);
		}
		for(Tuple tuple1:tuples){
			i=0;
			if(tuplesResult.isEmpty()==true){
				tuplesResult.add(tuple1);
				j++;
				continue;
			}
		 
	    	
			System.out.println("");
			    for(Attribute a:tuple1.getAttributeList()){
			    	for(int m=0;m<tuplesResult.get(i).getAttributeList().size();m++){
			    		if(a.getAttributeName().equals(this.orderPredicate) &&tuplesResult.get(i).getAttributeList().get(m).getAttributeName().equals(orderPredicate)){
			    			if(a.getAttributeValue().toString().compareTo(tuplesResult.get(i).getAttributeList().get(m).getAttributeValue().toString())>0){
			    				i++;
			    				m--;
						    	if(i==j){
						    		tuplesResult.add(i, tuple1);
						    		j++;
						    		break;}
			    				continue;
			    			}
			    			tuplesResult.add(i, tuple1);
			    			j++;
			    			i=0;
			    			break;
			    		}
			    	}
			    }
				
			
			}
		



			}
			
	
	
	
	/**
     * The function is used to return the sorted tuple
     * @return tuple
     */
	@Override
	public Tuple next(){
		if(count==tuplesResult.size())
			return null;
		Tuple tuple=tuplesResult.get(count);
		count++;
		return tuple;
		
	}
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return child.getAttributeList();
	}

}