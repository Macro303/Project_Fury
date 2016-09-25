package tobedevelopers.project_fury.settings.implementation;

import java.lang.ref.WeakReference;

import tobedevelopers.project_fury.model.Model;
import tobedevelopers.project_fury.model.ModelContract;
import tobedevelopers.project_fury.settings.SettingsContract;

/**
 * Created by Macro303 on 12/08/2016.
 */
public class SettingsPresenter implements SettingsContract.Presenter{

	private WeakReference< SettingsContract.View > viewWeakReference;
	private WeakReference< SettingsContract.Navigation > navigationWeakReference;
	private ModelContract model;

	public SettingsPresenter( SettingsContract.View view, SettingsContract.Navigation navigation ){
		this.viewWeakReference = new WeakReference<>( view );
		this.navigationWeakReference = new WeakReference<>( navigation );
		this.model = new Model();
	}
}
