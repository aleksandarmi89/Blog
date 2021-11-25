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

    <title>Cubes school - Blog project</title>
  <!-- Select2 -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/plugins/select2/css/select2.min.css">
  <!-- Font Awesome Icons -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/plugins/fontawesome-free/css/all.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/dist/css/adminlte.min.css">
   <!-- Summer note -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/plugins/summernote/summernote-bs4.css">
  <!-- Google Font: Source Sans Pro -->
  <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
  

  

<style>
.error{color:red}
</style>

<!-- KRAJ -->
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
            <h1>Blog Form</h1>
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
                <h3 class="card-title">Blog Form</h3>
              </div>
              <!-- /.card-header -->
              
              
              
              
              <!-- form start -->
              <form:form action="blog-save" role="form" modelAttribute="blog">
              	<form:hidden path="id"/>
              	
                <div class="card-body">
                  <div class="row">
                    <div class="col-md-6">
                      <div class="form-group">
                        <label>Title</label>

                        <form:input type="text" class="form-control" placeholder="Enter title" path="title"/>
                                                <form:errors path="title" cssClass="error"></form:errors>
                      </div>
                     
                         <div class="form-group">
                        <label>View</label>
                        <form:input class="form-control" readonly="true"  path="views"/>
                       
                      </div>
                      
                      
                      
					<div class="form-group">
                        <label>Blog Category</label>
                        <form:select class="form-control" path="category.id" itemLabel="name" itemValue="id" items="${categories}"/>
                      
                      </div>
                      <div class="form-group">
                        <label>Author</label>
                        <form:input  class="form-control" readonly="true" path="user" itemLabel="name" itemValue="username" items="${users}"/>
                      
                      </div>
                       <div class="form-group">
                        <label>Description</label>
                        <form:textarea type="text" class="form-control" placeholder="Enter description " path="description"/>
                        <form:errors path="description" cssClass="error"></form:errors>
                      </div>
                     

					  <div class="form-group">
                        <label>Image</label>
                        <form:input type="text" class="form-control" placeholder="Enter image url" path="image"/>
                      </div>
                   
         
           	    <div class="form-group" data-select2-id="74">
                  <label>Tags</label>
                  <form:select  class="select2 select2-hidden-accessible" multiple="true" data-placeholder="Select TAGS" style="width: 100%;" data-select2-id="7" tabindex="-1" aria-hidden="true" itemLabel="name" itemValue="id" items="${tags}" path="tags"/>
                  
                </div>
                
                
                
                
                
                 <div class="form-group">
                 <label>Post</label>
                <div class="card-body pad">
               
	              <div class="mb-3"></div>
	              <p class="text-sm mb-0">
	                Editor <a href="https://github.com/bootstrap-wysiwyg/bootstrap3-wysiwyg">Documentation and license
	                information.</a>
	                <form:textarea class="textarea" placeholder="Place some text here"
                          style="width: 100%; height: 200px; font-size: 14px; line-height: 18px; border: 1px solid #dddddd; padding: 10px;" path="post"></form:textarea>
	              </p>
	            </div>
               </div>
               
               
               
               
                
                  <div class="form-group">
                        <label>Active Blog</label>
                        <form:checkbox class="form-control" path="enabled"/>
                      </div>
                       <div class="form-group">
                        <label>Important Blog</label>
                        <form:checkbox class="form-control" path="important"/>
                      </div>
           	
                  	                 
                    </div>
                    
                  </div>
                  
                </div>
                <!-- /.card-body -->

                <div class="card-footer">
                  <button type="submit" class="btn btn-primary">Save</button>
                  <a href="blogs" class="btn btn-outline-secondary">Cancel</a>
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
<script src="${pageContext.request.contextPath}/admin/plugins/summernote/summernote-bs4.min.js"></script>
<!-- Summernote -->
<script>
  $(function () {
    // Summernote
    $('.textarea').summernote()
  })
</script>
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
