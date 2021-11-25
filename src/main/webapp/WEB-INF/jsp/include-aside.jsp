 
 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <aside class="col-lg-4">
          <!-- Widget [Search Bar Widget]-->
          <div class="widget search">
            <header>
              <h3 class="h6">Search the blog</h3>
            </header>
            <form action="${pageContext.request.contextPath}/blog-search" class="search-form">
              <div class="form-group">
                <input type="search" placeholder="What are you looking for?" name="text">
                <button type="submit" class="submit"><i class="icon-search"></i></button>
              </div>
            </form>
          </div>
          <!-- Widget [Latest Posts Widget]        -->
          <div class="widget latest-posts">
            <header>
              <h3 class="h6">Latest Posts</h3>
            </header>
            
            <c:forEach var="blog" items="${b}">
            <div class="blog-posts"><a href="${pageContext.request.contextPath}/blog-post/${blog.ceoTitle}/${blog.id}">
               
             
                <div class="item d-flex align-items-center">
                  <div class="image"><img src="${pageContext.request.contextPath}/front/img/${blog.image}" alt="..." class="img-fluid"></div>
                  <div class="title"><strong>${blog.title}</strong>
                    <div class="d-flex align-items-center">
                      <div class="views"><i class="icon-eye"></i> ${blog.views}</div>
                      <div class="comments"><i class="icon-comment"></i>${blog.numComents}</div>
                    </div>
                  </div>
                </div></a></div></c:forEach>
          </div>
          <!-- Widget [Categories Widget]-->
          <div class="widget categories">
            <header>
              <h3 class="h6">Categories</h3>
            </header>
            <c:forEach var="category" items="${categoriess}">
            <div class="item d-flex justify-content-between"><a href="${pageContext.request.contextPath}/blog-category/${category.ceoTitle}/${category.id}/1">${category.name}</a><span>${category.blogs.size()}</span></div>
           </c:forEach>
          </div>
          <!-- Widget [Tags Cloud Widget]-->
          <div class="widget tags">       
            <header>
              <h3 class="h6">Tags</h3>
            </header>
            <ul class="list-inline">
            <c:forEach var="tag" items="${tags}">
              <li class="list-inline-item"><a href="${pageContext.request.contextPath}/blog-tag/${tag.ceoTitle}/${tag.id}" class="tag">#${tag.name}(${tag.blogs.size()})</a></li>
              </c:forEach>
            </ul>
          </div>
        </aside>