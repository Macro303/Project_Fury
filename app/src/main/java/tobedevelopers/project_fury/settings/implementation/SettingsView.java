package tobedevelopers.project_fury.settings.implementation;

import android.os.Bundle;

import butterknife.ButterKnife;
import tobedevelopers.project_fury.BaseNavigationView;
import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.settings.SettingsContract;

/**
 * Created by Macro303 on 12/08/2016.
 */
public class SettingsView extends BaseNavigationView implements SettingsContract.View, SettingsContract.Navigation{

	private SettingsContract.Presenter presenter;

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		setTitle( getString( R.string.title_activity_settings ) );
		setContentView( R.layout.activity_settings );
		super.onCreate( savedInstanceState );
		presenter = new SettingsPresenter( this, this );

		ButterKnife.bind( this );
	}
}
