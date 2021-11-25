<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
     <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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

  <title>Blog project - Cubes School</title>

  <!-- Font Awesome Icons -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/plugins/fontawesome-free/css/all.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/dist/css/adminlte.min.css">
  <!-- Google Font: Source Sans Pro -->
  <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
</head>
<body class="hold-transition sidebar-mini">
<div class="wrapper">

  <jsp:include page="include-admin-menu.jsp"/>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1>Blogs</h1>
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
                <h3 class="card-title">Search Blogs</h3>
                <div class="card-tools">
                  <a href="blog-form" class="btn btn-success">
                    <i class="fas fa-plus-square"></i>
                    Add new Blog
                  </a>
                </div>
              </div>
              
              
              <!-- /.card-header -->
              
              
              
              <div class="card-body">
                <form id="entities-filter-form" method="GET" action="">
                  <div class="row">
                    <div class="col-md-3 form-group">
                      <label>Name</label>
                      <input type="text" class="form-control" placeholder="Search by name" name="title"
                      value="${titleSelected}"
                      
                      >
                    </div>
                    <div class="col-md-2 form-group">
                      <label>Category</label>
                      
                                <select class="form-control" name="category">

                               <option value="${null}">--ALL CATEGORY--</option>
                               
                                <c:forEach var="category" items="${categories}">
           					   
           					   <option value="${category.id}" <c:if test="${category.id ==  categorySelected}">selected="selected"</c:if>
           					   
           					   >${category.name}</option>
           					   
                                    </c:forEach>    
                                    
                                    
                                    
                                                        
                                </select>
                      
                      
                    </div>
                    
                     <sec:authorize access="hasRole('admin')">
                    <div class="col-md-2 form-group">
                      <label>Author</label>
                      <select class="form-control" name="username">

                        <option value="${null}">--ALL AUTHOR--</option>
                               
                           <c:forEach var="user" items="${users}">
           					   
           					   <option value="${user.username}" <c:if test="${user.username ==  userSelected}">selected="selected"</c:if>
           					   
           					   >${user.name} ${user.surname}</option>
           					   
                           </c:forEach>    
                      </select>                      
  
                    </div>
                    </sec:authorize>
                    
                    <div class="col-md-1 form-group">
                      <label>Enabled/Disabled</label>
                      <select class="form-control" name="enabled">
                        <option value="${null}" <c:if test="${enabledSelected == null}">selected="selected"</c:if>>-- All --</option>
                        <option value="${true}" <c:if test="${enabledSelected == true}">selected="selected"</c:if>>Enabled</option>
                        <option value="${false}" <c:if test="${enabledSelected == false}">selected="selected"</c:if>>Disabled</option>
                      </select>
                    </div>

                  </div>
                  <button type="submit" name="filter-form" value="filter"
                                class="btn btn-success text-uppercase">Apply filter</button>
                </form>
              </div>
              <!-- /.card-body -->
              <div class="card-footer clearfix">
                
              </div>
            </div>  
          
          
          
            <div class="card">
              <div class="card-header">
                <h3 class="card-title">All Blogs</h3>
                <div class="card-tools">
                  <a href="blog-form" class="btn btn-success">
                    <i class="fas fa-plus-square"></i>
                    Add new Blog
                  </a>
                </div>
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <table class="table table-bordered">
                  <thead>                  
                    <tr>
                      <th class="text-center">ID</th>
                      <th class="text-center">Title</th>
                      <th class="text-center">Image</th>
                      <th class="text-center">Category</th>
                      <th class="text-center">Author</th>
                      <th class="text-center">Date created</th>
                      <th class="text-center">Actions</th>
                    </tr>
                  </thead>
                  <tbody>
                  
                 
                  
                  <sec:authorize access="hasRole('admin')">
                  
                  <c:forEach var="blog" items="${blogs}">

                  
                    <tr>
                      <td>${blog.id}</td>
                      
                      <td>
                        <strong>${blog.title}</strong>
                      </td>
                      <td>${blog.image}</td>
                      <td>${blog.category.name}</td>
                      <td>${blog.user.name}</td>
					  <td><fmt:formatDate value="${blog.dateCreated}" pattern="dd/MMM/yyyy"/></td>
                  
                      <td class="text-center">
                        <div class="btn-group">
 						  
 					<c:choose>
                          <c:when test="${blog.enabled}">
                          <a href="blog-enabled?id=${blog.id}" class="btn btn-info"> 
                          <i class="far fa-eye"></i>
                          </a>
                          </c:when>
                          <c:otherwise>                      
                          <a href="blog-enabled?id=${blog.id}" class="btn btn-danger">
                          <i class="far fa-eye"></i>
                          </a>
                          </c:otherwise>                        
                     </c:choose>
                     
 					<c:choose>
                          <c:when test="${blog.important}">
                          <a href="blog-important?id=${blog.id}" class="btn btn-info"> 
                          <i class="fa fa-flag" aria-hidden="true"></i>
                          </a>
                          </c:when>
                          <c:otherwise>                      
                          <a href="blog-important?id=${blog.id}" class="btn btn-danger">
                          <i class="fa fa-flag" aria-hidden="true"></i>
                          </a>
                          </c:otherwise>                        
                     </c:choose> 
                     <!-- DA NE MOZE ADMIN DA RADI UPDATE TUDJEG BLOGA, isao sam sledecom logikom: -->                    
 					<c:choose>	
 					   <c:when test="${blog.user == currentUser}">  
                          <a href="blog-update?id=${blog.id}" class="btn btn-info">
                            <i class="fas fa-edit"></i>
                          </a>
                       </c:when>
                        <c:otherwise> 
                               <a href="#" class="btn btn-danger">
                            <i class="fas fa-edit"></i>
                          </a>
                        
                        </c:otherwise> 
                     </c:choose>      
                          <a href="blog-delete?id=${blog.id}">
                          <button type="button" class="btn btn-info" data-toggle="modal" data-target="#delete-modal">
                            <i class="fas fa-trash"></i>
                          </button>
                          </a>
                        </div>
                      </td>
                    </tr>
                    
                    </c:forEach>
                    
                    
                    </sec:authorize>
                    
                    
                  
                  <sec:authorize access="hasRole('bloger')">
                  
                  <c:forEach var="blog" items="${blogs1}">

                  
                    <tr>
                      <td>${blog.id}</td>
                      
                      <td>
                        <strong>${blog.title}</strong>
                      </td>
                      <td>${blog.image}</td>
                      <td>${blog.category.name}</td>
                      <td>${blog.user.name}</td>
					  <td><fmt:formatDate value="${blog.dateCreated}" pattern="dd/MMM/yyyy"/></td>
                  
                      <td class="text-center">
                        <div class="btn-group">
 						  
 					<c:choose>
                          <c:when test="${blog.enabled}">
                          <a href="blog-enabled?id=${blog.id}" class="btn btn-info"> 
                          <i class="far fa-eye"></i>
                          </a>
                          </c:when>
                          <c:otherwise>                      
                          <a href="blog-enabled?id=${blog.id}" class="btn btn-danger">
                          <i class="far fa-eye"></i>
                          </a>
                          </c:otherwise>                        
                     </c:choose>
                     
 					<c:choose>
                          <c:when test="${blog.important}">
                          <a href="blog-important?id=${blog.id}" class="btn btn-info"> 
                          <i class="fa fa-flag" aria-hidden="true"></i>
                          </a>
                          </c:when>
                          <c:otherwise>                      
                          <a href="blog-important?id=${blog.id}" class="btn btn-danger">
                          <i class="fa fa-flag" aria-hidden="true"></i>
                          </a>
                          </c:otherwise>                        
                     </c:choose>                     
 						  
                          <a href="blog-update?id=${blog.id}" class="btn btn-info">
                            <i class="fas fa-edit"></i>
                          </a>
                          <a href="blog-delete?id=${blog.id}">
                          <button type="button" class="btn btn-info" data-toggle="modal" data-target="#delete-modal">
                            <i class="fas fa-trash"></i>
                          </button>
                          </a>
                        </div>
                      </td>
                    </tr>
                    
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

    <div class="modal fade" id="delete-modal">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h4 class="modal-title">Delete Product</h4>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <p>Are you sure you want to delete product?</p>
            <strong></strong>
          </div>
          <div class="modal-footer justify-content-between">
            <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
            <button type="button" class="btn btn-danger">Delete</button>
          </div>
        </div>
        <!-- /.modal-content -->
      </div>
      <!-- /.modal-dialog -->
    </div>
    <!-- /.modal -->
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