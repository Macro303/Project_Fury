package tobedevelopers.project_fury.runnable_param;

/**
 * Created by Macro303 on 23/08/2016.
 */
public abstract class Runnable2Param< PARAM1, PARAM2 > implements Runnable{

	private PARAM1 param1;
	private PARAM2 param2;

	public Runnable2Param( PARAM1 param1, PARAM2 param2 ){
		this.param1 = param1;
		this.param2 = param2;
	}

	protected PARAM1 getParam1(){
		return param1;
	}

	protected PARAM2 getParam2(){
		return param2;
	}
}
