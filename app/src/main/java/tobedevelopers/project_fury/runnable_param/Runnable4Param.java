package tobedevelopers.project_fury.runnable_param;

/**
 * Created by Macro303 on 2/09/2016.
 */
public abstract class Runnable4Param< PARAM1, PARAM2, PARAM3, PARAM4 > extends Runnable3Param< PARAM1, PARAM2, PARAM3 >{

	private PARAM4 param4;

	public Runnable4Param( PARAM1 param1, PARAM2 param2, PARAM3 param3, PARAM4 param4 ){
		super( param1, param2, param3 );
		this.param4 = param4;
	}

	protected PARAM4 getParam4(){
		return param4;
	}
}
