package tobedevelopers.project_fury.runnable_param;

/**
 * Created by Macro303 on 2/09/2016.
 */
public abstract class Runnable5Param< PARAM1, PARAM2, PARAM3, PARAM4, PARAM5 > extends Runnable4Param< PARAM1, PARAM2, PARAM3, PARAM4 >{

	private PARAM5 param5;

	public Runnable5Param( PARAM1 param1, PARAM2 param2, PARAM3 param3, PARAM4 param4, PARAM5 param5 ){
		super( param1, param2, param3, param4 );
		this.param5 = param5;
	}

	protected PARAM5 getParam5(){
		return param5;
	}
}
