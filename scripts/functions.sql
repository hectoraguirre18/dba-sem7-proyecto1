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

CREATE OR REPLACE PROCEDURE FN_UPDATEDEPT(
    dept_no IN NUMBER,
    dept_name IN VARCHAR2,
    dept_loc IN VARCHAR2
) IS BEGIN
    UPDATE DEPT
    SET DEPTNO = dept_no, DNAME = dept_name, LOC = dept_loc
    WHERE DEPTNO = dept_no;
END;
