angular.module('app', []).controller('indexController', function ($scope, $http) {


    $scope.loadStudents = function () {
        $http({
            url: 'http://localhost:8189/api/v1/students',
            method: 'GET'
        }).then(function (response) {
            $scope.Students = response.data;
        });
    }

     $scope.deleteStudent = function (studentId) {
            $http.delete('http://localhost:8189/api/v1/students/' + studentId)
                .then(function (response) {
                    $scope.loadStudents();
                     $scope.newStudent.name=null;
                     $scope.newStudent.age=null;
                     $scope.newStudent.id=null;
                });
        }

      $scope.save = function () {
                 console.log($scope.newStudent);
                 if($scope.newStudent.name==null || $scope.newStudent.age==null){
                    $scope.newStudent.name=null;
                                                                            $scope.newStudent.age=null;
                                                                            $scope.newStudent.id=null;
                    return;
                 }
                 if($scope.newStudent.id==null){
                        $http.post('http://localhost:8189/api/v1/students', $scope.newStudent)
                            .then(function (response) {
                                    $scope.newStudent.name=null;
                                    $scope.newStudent.age=null;
                                    $scope.newStudent.id=null;
                                    $scope.loadStudents();
                     });
                 } else {
                    $http.put('http://localhost:8189/api/v1/students', $scope.newStudent)
                                                .then(function (response) {
                                                        $scope.newStudent.name=null;
                                                        $scope.newStudent.age=null;
                                                        $scope.newStudent.id=null;
                                                        $scope.loadStudents();
                                         });
                 }

             }

     $scope.editStudent = function (studentId) {
                      console.log(studentId);

                       $http({
                                  url: 'http://localhost:8189/api/v1/students/' + studentId,
                                  method: 'GET'
                              }).then(function (response) {
                                  $scope.newStudent = response.data;
                              });


                  }

     $scope.isAdding = function () {
             if ($scope.newStudent.id==null) {
                 return true;
             } else {
                 return false;
             }
         };

     $scope.clearFields= function(){
            $scope.newStudent.name=null;
            $scope.newStudent.age=null;
            $scope.newStudent.id=null;
     };

    $scope.loadStudents();

     $scope.newStudent = {
                        id: null,
                        name: null,
                        age: null
                    };

});