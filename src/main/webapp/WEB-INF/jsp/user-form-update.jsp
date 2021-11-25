<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta http-equiv="x-ua-compatible" content="ie=edge">

    <title>Cubes school - Shop project</title>
  <!-- Select2 -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/plugins/select2/css/select2.min.css">
  <!-- Font Awesome Icons -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/plugins/fontawesome-free/css/all.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/dist/css/adminlte.min.css">
  
  <!-- Google Font: Source Sans Pro -->
  <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
  

</head>
<body class="hold-transition sidebar-mini">
<div class="wrapper">

  
       <jsp:include page='include-admin-menu.jsp'/>   
       
  

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1>User Form</h1>
          </div>
        </div>
      </div><!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <div class="row">
          <div class="col-md-12">
            <div class="card card-primary">
              <div class="card-header">
                <h3 class="card-title">User Form</h3>
              </div>
              <!-- /.card-header -->
              
                  <td class="text-center">
                        <img src="${pageContext.request.contextPath}/front/img/${user.image}" style="max-width: 150px;">
                      </td>
              
              
              <!-- form start -->
              <form:form action="user-save" role="form" modelAttribute="user">
              <form:hidden path="password"/>
       
              	 <form:hidden path="username"/>
                <div class="card-body">
                  <div class="row">
                    <div class="col-md-6">
                     <div class="callout callout-success">
                      <h5>Username</h5>

                      <p>${user.username}</p>
                </div>
                       <div class="form-group">
                        <label>Name</label>
                        <form:input type="text" class="form-control" placeholder="Enter name" path="name"/>
                      </div>
                      <!--  div class="form-group">
                        <label>Image file</label>
                        <form:input type="file" class="form-control" placeholder="File no selected" path="file"/>
                      </div>-->
                       <div class="form-group">
                        <label>Surname</label>
                        <form:input type="text" class="form-control" placeholder="Enter surname" path="surname"/>
                      </div>
                       <div class="form-group">
                        <label>Image</label>
                        <form:input type="text" class="form-control"  placeholder="Enter image url" path="image"/>
                      </div>
                        <div class="form-group">
                        <label>Phone</label>
                        <form:input type="text" class="form-control" placeholder="Enter phone" path="phone"/>
                       
                      </div>
                       <div class="form-group">
                        <label>email</label>
                        <form:input type="text" class="form-control" placeholder="Enter email" path="email"/>
                      </div>
                   
                        
					<c:choose><c:when test="${isAdmin}">
				<div class="form-group">
                        <label>User Role</label>
                        <form:select class="form-control" path="roles" itemLabel="title" itemValue="authority" items="${userRoles}"
                        multiple="false"/>
                      
                      </div>
                      
                      <!--  div class="form-group">
                        <label>User Role</label>
                        <form:select class="form-control" path="roles"  multiple="false">
                        <c:forEach var="r" items="${userRoles}">
                        <c:choose>
                        <c:when test="${r.authority.equals(user.roles.get(0).authority)}">
                        <form:option value="${r.authority }" label="${r.title}" selected="selected">
                        </form:option>
                        </c:when>
                        <c:otherwise>
                        
                        <form:option value="${r.authority }" label="${r.title}">
                        </form:option>
                        </c:otherwise>
                        
                        </c:choose>
                        
                        
                        </c:forEach>
                        
                      </form:select>
                      </div>-->
					
					</c:when>
					<c:otherwise>
					
				<form:hidden path="roles"/>
					<input type="hidden" value="${roles}"/>
                       <div class="callout callout-success">
                      <h5>ROLA</h5>

                      <p>${user.roles.get(0).title}</p>
                </div>
                
					</c:otherwise>
					</c:choose>
                     
                     
					 
                   
         
           	    
                
                 
                  	                 
                    </div>
                    
                  </div>
                  
                </div>
                <!-- /.card-body -->

                <div class="card-footer">
                  <button type="submit" class="btn btn-primary">Save</button>
                  <a href="users" class="btn btn-outline-secondary">Cancel</a>
                </div>
              </form:form>
              <!-- Form end -->
              
              
              
              
            </div>
            <!-- /.card -->
          </div>
          <!-- /.col -->
        </div>
        <!-- /.row -->
      </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  

  <!-- Main Footer -->
  <footer class="main-footer">
    <!-- To the right -->
    <div class="float-right d-none d-sm-inline">
      Java
    </div>
    <!-- Default to the left -->
    <strong>Copyright &copy; 2019 <a href="https://cubes.edu.rs">Cubes School</a>.</strong> All rights reserved.
  </footer>
</div>
<!-- ./wrapper -->

<!-- REQUIRED SCRIPTS -->

<!-- jQuery -->
<script src="${pageContext.request.contextPath}/admin/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="${pageContext.request.contextPath}/admin/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath}/admin/dist/js/adminlte.min.js"></script>
<!-- Tempusdominus Bootstrap 4 -->
<script src="${pageContext.request.contextPath}/admin/plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
<!-- Select2 -->
<script src="${pageContext.request.contextPath}/admin/plugins/select2/js/select2.full.min.js"></script>

<!-- Page script -->
<script>
  $(function () {
    //Initialize Select2 Elements
    $('.select2').select2()

    //Initialize Select2 Elements
    $('.select2bs4').select2({
      theme: 'bootstrap4'
    })

    
  })
  
</script>
</body>
</html>
