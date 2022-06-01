package com.fit_web_app.models.enums;

public enum AdminEndpoint {
    getAllUsers("/getAllUsers"),
    registerUser("/registerUser"),
    deleteUser("/deleteUser"),
    getAvailableRoles("/getAvailableRoles"),
    addCourse("/addCourse"),
    deleteCourse("/deleteCourse"),
    editCourse("/editCourse"),
    addStep("/addStep"),
    deleteStep("/deleteStep"),
    editStep("/editStep");

    private final String endpoint;

    AdminEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public static boolean checkAccess(String endpoint) {
        if (endpoint == null) return false;

        for (AdminEndpoint e : AdminEndpoint.values()) {
            if (e.endpoint.equals(endpoint)) {
                return true;
            }
        }

        return false;
    }
}
