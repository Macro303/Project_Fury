package tobedevelopers.project_fury.runnable_param;

/**
 * Created by Macro303 on 2/09/2016.
 */
public abstract class Runnable6Param< PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, PARAM6 > extends Runnable5Param< PARAM1, PARAM2, PARAM3, PARAM4, PARAM5 >{

	private PARAM6 param6;

	public Runnable6Param( PARAM1 param1, PARAM2 param2, PARAM3 param3, PARAM4 param4, PARAM5 param5, PARAM6 param6 ){
		super( param1, param2, param3, param4, param5 );
		this.param6 = param6;
	}

	protected PARAM6 getParam6(){
		return param6;
	}
}
