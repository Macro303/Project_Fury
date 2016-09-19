package tobedevelopers.project_fury.runnable_param;

/**
 * Created by A on 9/13/2016.
 */
public abstract class Runnable7Param< PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, PARAM6, PARAM7 > extends Runnable6Param< PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, PARAM6 >{
	private PARAM7 param7;

	public Runnable7Param( PARAM1 param1, PARAM2 param2, PARAM3 param3, PARAM4 param4, PARAM5 param5, PARAM6 param6, PARAM7 param7 ){
		super( param1, param2, param3, param4, param5, param6 );
		this.param7 = param7;
	}

	protected PARAM7 getParam7(){
		return param7;
	}
}
