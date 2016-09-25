package tobedevelopers.project_fury.runnable_param;

/**
 * Created by Macro303 on 23/08/2016.
 */
public abstract class Runnable2Param< PARAM1, PARAM2 > extends Runnable1Param< PARAM1 > implements Runnable{

	private PARAM2 param2;

	public Runnable2Param( PARAM1 param1, PARAM2 param2 ){
		super( param1 );
		this.param2 = param2;
	}

	protected PARAM2 getParam2(){
		return param2;
	}
}
