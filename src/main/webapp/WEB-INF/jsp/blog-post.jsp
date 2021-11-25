<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Blog </title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="robots" content="all,follow">
    <!-- Bootstrap CSS-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/front/vendor/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome CSS-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/front/vendor/font-awesome/css/font-awesome.min.css">
    <!-- Custom icon font-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/front/css/fontastic.css">
    <!-- Google fonts - Open Sans-->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
    <!-- Fancybox-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/front/vendor/@fancyapps/fancybox/jquery.fancybox.min.css">
    <!-- theme stylesheet-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/front/css/style.default.css" id="theme-stylesheet">
    <!-- Custom stylesheet - for your changes-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/front/css/custom.css">
    <!-- Favicon-->
    <link rel="shortcut icon" href="favicon.png">
    <!-- Tweaks for older IEs--><!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->
  </head>
  <body>
   <jsp:include page='include-header-menu.jsp'/> 

    <div class="container">
      <div class="row">
        <!-- Latest Posts -->
        <main class="post blog-post col-lg-8"> 
          <div class="container">
            <div class="post-single">
              <div class="post-thumbnail"><img src="${pageContext.request.contextPath}/front/img/${blog.image}" alt="..." class="img-fluid"></div>
              <div class="post-details">
                <div class="post-meta d-flex justify-content-between">
                 <c:choose>
                  <c:when test="${!blog.category.name.equals('Uncategorized')}"> 
                  <div class="category">   <a href="${pageContext.request.contextPath}/blog-category/${blog.category.ceoTitle}/${blog.category.id}/1"> ${blog.category.name}</a></div>
                  </c:when>
                  <c:otherwise>
                   <div class="category">
					<b>UNCATEGORIZED</b>
               		</div>      
               		
               		</c:otherwise></c:choose>
                </div>
                <h1>${blog.title}<a href="#"><i class="fa fa-bookmark-o"></i></a></h1>
                <div class="post-footer d-flex align-items-center flex-column flex-sm-row"><a href="${pageContext.request.contextPath}/blog-author/${blog.user.ceoTitle}/${blog.user.username}/1" class="author d-flex align-items-center flex-wrap">
                    <div class="avatar"><img src="${pageContext.request.contextPath}/front/img/${blog.user.image}" alt="..." class="img-fluid"></div>
                    <div class="title"><span>${blog.user.name} ${blog.user.surname}</span></div></a>
                  <div class="d-flex align-items-center flex-wrap">       
                    <div class="date"><i class="icon-clock"></i> ${blog.timeAgo}</div>
                    <div class="views"><i class="icon-eye"></i> ${blog.views}</div>
                    <div class="comments meta-last"><a href="#"><i class="icon-comment"></i>${blog.numComents}</a></div>
                  </div>
                </div>
                <div class="post-body">
                 ${blog.post}
                </div>
                
                
                <div  class="post-tags"> <c:forEach var="bt" items="${blog.tags}"><a href="${pageContext.request.contextPath}/blog-tag/${bt.ceoTitle}/${bt.id}" class="tag">#${bt.name}</a> </c:forEach></div>
                
               
                <div class="posts-nav d-flex justify-content-between align-items-stretch flex-column flex-md-row"><a href="${pageContext.request.contextPath}/blog-post/${ba.ceoTitle}/${ba.id}" class="prev-post text-left d-flex align-items-center">
                    <div class="icon prev"><i class="fa fa-angle-left"></i></div>
                    
                    <div class="text"><strong class="text-primary">Previous Post </strong> 
                      <h6>${ba.title}.</h6>
                    </div>  
                    </a><a href="${pageContext.request.contextPath}/blog-post/${ban.ceoTitle}/${ban.id}" class="next-post text-right d-flex align-items-center justify-content-end">
                    <div class="text"><strong class="text-primary">Next Post </strong>
                      <h6>${ban.title}.</h6>
                    </div>
                    <div class="icon next"><i class="fa fa-angle-right">   </i></div></a></div>
                <div class="post-comments" id="post-comments">
                  <header>
                   
                   
                    <h3 class="h6">Post Comments<span class="no-of-comments">(${blog.numComents})</span></h3>
                    
                    
                  </header>
                  <c:forEach var="c" items="${blog.comments}">
                  <c:if test="${c.enabled}">
                  <div class="comment">
                    <div class="comment-header d-flex justify-content-between">
                      <div class="user d-flex align-items-center">
                        <div class="image"><img src="${pageContext.request.contextPath}/front/img/user.svg" alt="..." class="img-fluid rounded-circle"></div>
                        <div class="title"><strong> ${c.name}</strong><span class="date"><fmt:formatDate value="${c.dateCreated}" pattern=" MMM yyyy"/></span></div>
                      </div>
                    </div>
                    <div class="comment-body">
                      <p>${c.description}</p>
                    </div>
                  </div>
                  </c:if>
                  </c:forEach>
                
                </div>
                <div class="add-comment">
                  <header>
                    <h3 class="h6">Leave a reply</h3>
                  </header>
                  <form:form method="post" action="/comment-save/${blog.ceoTitle}/${blog.id}" modelAttribute="comment" class="commenting-form">
                    <div class="row">
                      <div class="form-group col-md-6">
                      <form:hidden path="id"/>
                        <form:input type="text"  placeholder="Name" class="form-control" path="name"/>
                      </div>
                      <div class="form-group col-md-6">
                        <form:input type="email"  placeholder="Email Address (will not be published)" class="form-control" path="email"/>
                      </div>
                      <div class="form-group col-md-12">
                        <form:textarea  placeholder="Type your comment" class="form-control" path="description"></form:textarea>
                      </div>
                      <div class="form-group col-md-12">
                        <button type="submit" class="btn btn-secondary">Submit Comment</button>
                      </div>
                    </div>
                  </form:form>
                </div>
              </div>
            </div>
          </div>
        </main>
        <jsp:include page='include-aside.jsp'/>
      </div>
    </div>
    <!-- Page Footer-->
   <jsp:include page='include-footer-menu.jsp'/> 
    <!-- JavaScript files-->
    <script src="${pageContext.request.contextPath}/front/vendor/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/front/vendor/popper.js/umd/popper.min.js"> </script>
    <script src="${pageContext.request.contextPath}/front/vendor/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/front/vendor/jquery.cookie/jquery.cookie.js"> </script>
    <script src="${pageContext.request.contextPath}/front/vendor/@fancyapps/fancybox/jquery.fancybox.min.js"></script>
    <script src="${pageContext.request.contextPath}/front/js/front.js"></script>
  </body>
</html>