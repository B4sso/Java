public class Dataset {
	
	public Dataset(Value val)
	{
		count++;
		sum = val.getVal();
		minimum = val;
		maximum = val;
	}
	public Dataset()
	{
		count = 0;
		sum = 0;
	}
	public void add(Value val)
	{
		double tmp = val.getVal();
		sum = sum + tmp;
		if (count == 0 || tmp > maximum.getVal())
			maximum = val;
		if (count == 0 || tmp < minimum.getVal())
			minimum = val;
		count ++;
	}
	public Value get(int flag)
	{
		if (flag <= 0)
			return minimum;
		return maximum;
	}
	
	private Value minimum;
	private Value maximum;
	private int count;
	private double sum;
}
