package tobedevelopers.project_fury;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import tobedevelopers.project_fury.ModelTests.ModelTestColumn;
import tobedevelopers.project_fury.ModelTests.ModelTestProject;
import tobedevelopers.project_fury.ModelTests.ModelTestTask;
import tobedevelopers.project_fury.ModelTests.ModelTestUser;

/**
 * Created by Macro303 on 16/08/2016.
 */
@RunWith( Suite.class )
@Suite.SuiteClasses( { ModelTestUser.class, ModelTestProject.class, ModelTestTask.class, ModelTestColumn.class } )
public class ModelUnitTests{
}
