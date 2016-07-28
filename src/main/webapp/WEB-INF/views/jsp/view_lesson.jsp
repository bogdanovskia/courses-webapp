<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${lesson.getTitle()}</title>
</head>
<body>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>No.</th>
				<th>File Name</th>
				<th>Type</th>
				<th>Description</th>
				<th width="100"></th>
				<th width="100"></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${lesson.getLessonDocuments()}" var="doc"
				varStatus="counter">

				<c:if test="${!doc.getType().equals('video/mp4') || doc.getType().equals('video/mp4') && !sessionScope.loggedUser.isStudent()}">

					<tr>
						<td>${counter.index + 1}</td>
						<td>${doc.name}</td>
						<td>${doc.type}</td>
						<td>${doc.description}</td>
						<td><a
							href="<c:url value='${lesson.getId()}/download-document-${doc.getId()}' />"
							class="btn btn-success custom-width">download</a></td>
						<c:if test="${!sessionScope.loggedUser.isStudent()}">
							<td><a
								href="<c:url value='${lesson.getId()}/delete-document-${doc.getId()}' />"
								class="btn btn-danger custom-width">delete</a></td>
						</c:if>
					</tr>

				</c:if>
			</c:forEach>
		</tbody>
	</table>

	<c:forEach items="${videos}" var="video">
		<video width="320" height="240" controls> <source
			src="${lesson.getId()}/download-document-${video.getId()}"
			type="video/mp4"></video>
		<br />
		<br />
	</c:forEach>

	<c:if test="${!sessionScope.loggedUser.isStudent()}">
		<div class="panel panel-default">

			<div class="panel-heading">
				<span class="lead">Upload New Document</span>
			</div>
			<div class="uploadcontainer">
				<form:form method="POST" modelAttribute="fileBucket"
					enctype="multipart/form-data" class="form-horizontal">

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-3 control-lable" for="file">Upload a
								document</label>
							<div class="col-md-7">
								<form:input type="file" path="file" id="file"
									class="form-control input-sm" />
								<div class="has-error">
									<form:errors path="file" class="help-inline" />
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-3 control-lable" for="file">Description</label>
							<div class="col-md-7">
								<form:input type="text" path="description" id="description"
									class="form-control input-sm" />
							</div>

						</div>
					</div>

					<div class="row">
						<div class="form-actions floatRight">
							<input type="submit" value="Upload"
								class="btn btn-primary btn-sm">
						</div>
					</div>

				</form:form>
			</div>
		</div>
	</c:if>
	<a href = "${lesson.getId()}/delete-lesson">Delete lesson</a>
	<a href = "../../${lesson.getCourse().getId()}">Back to course page</a>
</body>
</html>