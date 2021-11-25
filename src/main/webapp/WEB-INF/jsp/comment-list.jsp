<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
     <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
            <h1>Comments</h1>
          </div>
        </div>
      </div><!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <div class="row">
          <div class="col-md-12">
            <div class="card">
              <div class="card-header">
                <h3 class="card-title">All Comment</h3>
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <table class="table table-bordered">
                  <thead>                  
                    <tr>
                      <th class="text-center">ID</th>
                      <th class="text-center">Name</th>
                      <th class="text-center">Email</th>
                      <th class="text-center">Comment</th>
                     <th class="text-center">Blog title</th>
                      <th class="text-center">Actions</th>
                    </tr>
                  </thead>
                  <tbody>
                   <tbody>
                    <sec:authorize  access="hasRole('admin')">
                  <c:forEach var="comment" items="${comments}">
                  
                  <c:choose>
                  
                  	<c:when test="${comment.seen}">
                  	 <tr>
                      <td>${comment.id}</td>
                      <td>${comment.name}</td>

                      <td>${comment.email}</td>
                      <td>${comment.description}</td>      
                       <td>${comment.blog.title}</td>   
                      <td class="text-center">
                        <div class="btn-group">
                        
                        <c:choose><c:when test="${comment.enabled}">
                            
                             <a href="comment-enabled?id=${comment.id}" class="btn btn-info">
                             <i class="far fa-eye"></i></a>
                          </c:when>
                          <c:otherwise>
                          
                           <a href="comment-enabled?id=${comment.id}" class="btn btn-danger">
                           <i class="far fa-eye-slash"></i>
                           </a>
                          </c:otherwise>
                          
                          </c:choose>
                          
                          Is seen
                        </div>
                      </td>
                   
                   
                   
                   
                    </tr>
                  	
                  	</c:when>
                  	<c:otherwise>
                  		<tr>
                      <td><strong>${comment.id}</strong></td>
                      <td><strong>${comment.name}</strong></td>
                      <td><strong>${comment.email}</strong></td>
                      <td><strong>${comment.description}</strong></td>
                        <td>${comment.blog.title}</td> 
                      <td class="text-center">
                        <div class="btn-group">
                        <c:choose><c:when test="${comment.enabled}">
                            
                             <a href="comment-enabled?id=${comment.id}" class="btn btn-info">
                             <i class="far fa-eye"></i></a>
                          </c:when>
                          <c:otherwise>
                          
                           <a href="comment-enabled?id=${comment.id}" class="btn btn-danger">
                           <i class="far fa-eye-slash"></i>
                           </a>
                          </c:otherwise>
                          
                          </c:choose>
                          
                        
                          <a href="comment-seen?id=${comment.id}" class="btn btn-info">
                            <i class="far fa-eye"></i>
                          </a>
                       
                          
                        </div>
                      </td>
                    </tr>
                  	
                  	</c:otherwise>
              
                  
                  </c:choose>
                    
                    </c:forEach>
                    </sec:authorize>
                     <tbody><sec:authorize  access="hasRole('bloger')">
                    <c:forEach var="comment" items="${bloger}">
                  
                  <c:choose>
                  
                  	<c:when test="${comment.seen}">
                  	 <tr>
                      <td>${comment.id}</td>
                      <td>${comment.name}</td>

                      <td>${comment.email}</td>
                      <td>${comment.description}</td>      
                       <td>${comment.blog.title}</td>   
                      <td class="text-center">
                        <div class="btn-group">
                        
                        <c:choose><c:when test="${comment.enabled}">
                            
                             <a href="comment-enabled?id=${comment.id}" class="btn btn-info">
                             <i class="far fa-eye"></i></a>
                          </c:when>
                          <c:otherwise>
                          
                           <a href="comment-enabled?id=${comment.id}" class="btn btn-danger">
                           <i class="far fa-eye-slash"></i>
                           </a>
                          </c:otherwise>
                          
                          </c:choose>
                          
                          Is seen
                        </div>
                      </td>
                   
                   
                   
                   
                    </tr>
                  	
                  	</c:when>
                  	<c:otherwise>
                  		<tr>
                      <td><strong>${comment.id}</strong></td>
                      <td><strong>${comment.name}</strong></td>
                      <td><strong>${comment.email}</strong></td>
                      <td><strong>${comment.description}</strong></td>
                        <td>${comment.blog.title}</td> 
                      <td class="text-center">
                        <div class="btn-group">
                        <c:choose><c:when test="${comment.enabled}">
                            
                             <a href="comment-enabled?id=${comment.id}" class="btn btn-info">
                             <i class="far fa-eye"></i></a>
                          </c:when>
                          <c:otherwise>
                          
                           <a href="comment-enabled?id=${comment.id}" class="btn btn-danger">
                           <i class="far fa-eye-slash"></i>
                           </a>
                          </c:otherwise>
                          
                          </c:choose>
                          
                        
                          <a href="comment-seen?id=${comment.id}" class="btn btn-info">
                            <i class="far fa-eye"></i>
                          </a>
                       
                          
                        </div>
                      </td>
                    </tr>
                  	
                  	</c:otherwise>
              
                  
                  </c:choose>
                    
                    </c:forEach>
                    </sec:authorize>
                  </tbody>
                </table>
              </div>
              <!-- /.card-body -->
              <div class="card-footer clearfix">
                
              </div>
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
</body>
</html>
