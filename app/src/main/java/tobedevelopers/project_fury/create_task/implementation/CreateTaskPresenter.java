package tobedevelopers.project_fury.create_task.implementation;

import java.lang.ref.WeakReference;

import tobedevelopers.project_fury.create_task.CreateTaskContract;
import tobedevelopers.project_fury.model.Model;
import tobedevelopers.project_fury.model.ModelContract;

/**
 * Created by Macro303 on 12/08/2016.
 */
public class CreateTaskPresenter implements CreateTaskContract.Presenter{

	private WeakReference< CreateTaskContract.View > viewWeakReference;
	private WeakReference< CreateTaskContract.Navigation > navigationWeakReference;
	private ModelContract model;

	private String mTaskName;
	private String mTaskDescription;

	public CreateTaskPresenter( CreateTaskContract.View view, CreateTaskContract.Navigation navigation ){
		this.viewWeakReference = new WeakReference<>( view );
		this.navigationWeakReference = new WeakReference<>( navigation );
		this.model = new Model();
	}

	@Override
	public void userSelectBack(){
		CreateTaskContract.View view = viewWeakReference.get();
		CreateTaskContract.Navigation navigation = navigationWeakReference.get();

		if( view != null && navigation != null )
			navigation.navigateToPrevious();
	}

	@Override
	public void userEnterTaskName( String taskName ){
		CreateTaskContract.View view = viewWeakReference.get();

		if( view != null ){
			mTaskName = taskName;
			if( taskName.length() < 3 ){
				view.setTaskNameUnderValidation();
				view.disableCreateTaskButton();
			}else if( taskName.length() > 20 ){
				view.disableCreateTaskButton();
			}else
				view.enableCreateTaskButton();
			if( taskName.length() >= 20 ){
				view.setTaskDescriptionOverValidation();
			}
		}
	}

	@Override
	public void userEnterTaskDescription( String taskDescription ){
		CreateTaskContract.View view = viewWeakReference.get();

		if( view != null ){
			mTaskDescription = taskDescription;
			if( taskDescription.length() > 128 ){
				view.disableCreateTaskButton();
			}
			if( taskDescription.length() >= 128 ){
				view.setTaskDescriptionOverValidation();
			}
		}
	}

	@Override
	public void userSelectCreateTask(){
		CreateTaskContract.View view = viewWeakReference.get();
		CreateTaskContract.Navigation navigation = navigationWeakReference.get();

		if( view != null && navigation != null )
			navigation.navigateToPreviousAfterCreate();
	}
}
