package tobedevelopers.project_fury.Runnable;

/**
 * Created by A on 8/9/2016.
 */
public abstract class Runnable1Param< PARAM1 > implements Runnable{

	private PARAM1 param1;

	public Runnable1Param( PARAM1 param1 ){
		this.param1 = param1;
	}

	protected PARAM1 getParam1(){
		return param1;
	}
}
