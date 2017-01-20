package com.yodle.android.bayonet;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.MockUtil;

public class DaggerMockProviderTest {

    private DaggerMockProvider daggerMockProvider;

    @Before
    public void setUp() {
        daggerMockProvider = new DaggerMockProvider();
    }

    @Test
    public void init_whenSuperClass_addsCorrectRealClassesFromInstanceAndSuperClasses() {
        daggerMockProvider.init(new TestCaseInstance());

        Set<Class> expectedRealClasses = new HashSet<>();
        expectedRealClasses.add(PersonService.class);
        expectedRealClasses.add(EmployeeService.class);
        expectedRealClasses.add(TeamService.class);
        assertEquals(expectedRealClasses, daggerMockProvider.getRealClasses());
    }

    @Test
    public void provide_whenRealClass_returnsRealInstance() {
        daggerMockProvider.init(new TestCaseInstance());

        PersonService expectedPersonService = new PersonService();
        PersonService actualPersonService = daggerMockProvider.provide(expectedPersonService);

        assertEquals(expectedPersonService, actualPersonService);
    }

    @Test
    public void provide_whenNotRealClass_returnsMockInstance() {
        daggerMockProvider.init(new TestCaseInstance());

        UserService actualUserService = daggerMockProvider.provide(new UserService());

        assertTrue(isMock(actualUserService));
    }

    private boolean isMock(Object object) {
        return new MockUtil().isMock(object);
    }

    private abstract class TopLevelTestCaseInstance {
        @RealClass PersonService personService;
        UserService userService;
    }

    private abstract class MiddleLevelTestCaseInstance extends TopLevelTestCaseInstance {
        @RealClass EmployeeService employeeService;
        OrganizationService organizationService;
    }

    private class TestCaseInstance extends MiddleLevelTestCaseInstance {
        @RealClass TeamService teamService;
        JobService jobService;
    }

    private class PersonService {
    }

    private class UserService {
    }

    private class EmployeeService {
    }

    private class OrganizationService {
    }

    private class TeamService {
    }

    private class JobService {
    }
}
