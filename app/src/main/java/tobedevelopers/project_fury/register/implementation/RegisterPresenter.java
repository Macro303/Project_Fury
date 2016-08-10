package tobedevelopers.project_fury.register.implementation;

import java.lang.ref.WeakReference;

import tobedevelopers.project_fury.register.RegisterContract;

/**
 * Created by Macro303 on 10/08/2016.
 */
public class RegisterPresenter implements RegisterContract.Presenter{

	private WeakReference< RegisterContract.View > viewWeakReference;
	private WeakReference< RegisterContract.Navigation > navigationWeakReference;

	public RegisterPresenter( RegisterContract.View view, RegisterContract.Navigation navigation ){
		this.viewWeakReference = new WeakReference<>( view );
		this.navigationWeakReference = new WeakReference<>( navigation );
	}

	@Override
	public void userSelectCreateAccount(){
		RegisterContract.View view = viewWeakReference.get();
		RegisterContract.Navigation navigation = navigationWeakReference.get();

		if( view != null && navigation != null )
			navigation.finish();
	}
}
