CREATE OR REPLACE FUNCTION FN_LIST RETURN SYS_REFCURSOR IS
    l_cursor SYS_REFCURSOR;
BEGIN
    OPEN l_cursor FOR SELECT deptno, dname, loc FROM dept ORDER BY deptno;
    RETURN l_cursor;
END;

CREATE OR REPLACE FUNCTION FN_FINDBYDEPTNO(
    dept_no IN NUMBER
) RETURN VARCHAR2 IS
    v_name VARCHAR2(14);
BEGIN
    SELECT dname
    INTO v_name
    FROM dept
    WHERE deptno = dept_no;

    RETURN v_name;
END;

CREATE OR REPLACE PROCEDURE FN_DELETEBYDEPNO(
    dept_no IN NUMBER
) IS BEGIN
    DELETE
    FROM DEPT
    WHERE deptno = dept_no;

END;

CREATE OR REPLACE PROCEDURE FN_INSERTDEPT(
    dept_no IN NUMBER,
    dept_name IN VARCHAR2,
    dept_loc IN VARCHAR2
) IS BEGIN
    INSERT INTO DEPT VALUES (dept_no, dept_name, dept_loc);
END;


-- Emp. Functions

CREATE OR REPLACE FUNCTION FN_EMPLIST RETURN SYS_REFCURSOR IS
    l_cursor SYS_REFCURSOR;
BEGIN
    OPEN l_cursor FOR SELECT empno, ename, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO FROM EMP ORDER BY empno;
    RETURN l_cursor;
END;

CREATE OR REPLACE FUNCTION FN_FINDBYEMPNO(
    emp_no IN NUMBER
) RETURN VARCHAR2 IS
    v_name VARCHAR2(14);
BEGIN
    SELECT ename
    INTO v_name
    FROM emp
    WHERE empno = emp_no;

    RETURN v_name;
END;



CREATE OR REPLACE PROCEDURE FN_DELETEBYEMP(
    emp_no IN NUMBER
) IS BEGIN
    DELETE
    FROM EMP
    WHERE empno = emp_no;
END;

CREATE OR REPLACE PROCEDURE FN_INSERTEMP(
    emp_no IN NUMBER,
    emp_name IN VARCHAR2,
    emp_job IN VARCHAR2,
    emp_mgr IN VARCHAR2,
    emp_hiredate IN VARCHAR2,
    emp_sal IN VARCHAR2,
    emp_comm IN VARCHAR2,
    emp_deptno IN VARCHAR2
) IS BEGIN
    INSERT INTO EMP VALUES (emp_no, emp_name, emp_job, emp_mgr, emp_hiredate, emp_sal, emp_co7mm, emp_deptno);
END;