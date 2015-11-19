package simpledatabase;
import java.util.ArrayList;

public class Join extends Operator{

	private ArrayList<Attribute> newAttributeList;
	private String joinPredicate;
	ArrayList<Tuple> tuples1;

	
	//Join Constructor, join fill
	public Join(Operator leftChild, Operator rightChild, String joinPredicate){
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.joinPredicate = joinPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuples1 = new ArrayList<Tuple>();
		while(true){
			Tuple tuple=leftChild.next();
			if(tuple==null)
				break;
			tuples1.add(tuple);

		}

		}

	
	/**
     * It is used to return a new tuple which is already joined by the common attribute
     * @return the new joined tuple
     */
	//The record after join with two tables
	@Override
	public Tuple next(){
		newAttributeList = new ArrayList<Attribute>();
		Tuple tuple1= rightChild.next();
		if(tuple1==null)
			return null;
		for (Attribute a:tuple1.getAttributeList()){
			for(Tuple tuple:tuples1){
				for(Attribute a1:tuple.getAttributeList()){
					if(a.getAttributeName().equals(a1.getAttributeName())&& a.getAttributeValue().equals(a1.getAttributeValue())){
						for (Attribute a2:tuple1.getAttributeList()){
							newAttributeList.add(a2);
						}
						for(Attribute a3:tuple.getAttributeList()){
							if(!a3.getAttributeName().equals(a.getAttributeName()))
							newAttributeList.add(a3);
						}
			}
			
		}
			}
		}
		tuple1=new Tuple(newAttributeList);
		return tuple1;
	}
	
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		if(joinPredicate.isEmpty())
			return child.getAttributeList();
		else
			return(newAttributeList);
	}

}