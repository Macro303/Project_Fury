package tobedevelopers.project_fury.runnable_param;

/**
 * Created by Macro303 on 2/09/2016.
 */
public abstract class Runnable3Param< PARAM1, PARAM2, PARAM3 > extends Runnable2Param< PARAM1, PARAM2 >{

	private PARAM3 param3;

	public Runnable3Param( PARAM1 param1, PARAM2 param2, PARAM3 param3 ){
		super( param1, param2 );
		this.param3 = param3;
	}

	protected PARAM3 getParam3(){
		return param3;
	}
}
