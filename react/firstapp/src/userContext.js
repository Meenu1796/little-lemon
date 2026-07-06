import { createContext, useContext, useState } from "react";

const UserContext = createContext(undefined);

export const UserProvider = ({ children }) => {
  const [user] = useState({ name: "John" });
  return (
    <UserContext.Provider value={{ user }}>{children}</UserContext.Provider>
  );
};

export const useUser = () => useContext(UserContext);
