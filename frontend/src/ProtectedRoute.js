import React, { useState } from "react";
import { Navigate, Outlet } from "react-router-dom";
import SearchPanel from "./pages/searchFilter/SearchPanel";

function ProtectedRoute() {
  const [showSearchPanel, setShowSearchPanel] = useState(true);
  const isAuthenticated = localStorage.getItem("isAuthenticated");
  console.log("this", isAuthenticated);
  return(<>
   {(showSearchPanel)?<div className="search-pin">
        <SearchPanel/>
      </div>:null}
    { isAuthenticated==='true'? <Outlet/> : <Navigate to="/login" />}
     </>
     );
}

export default ProtectedRoute;