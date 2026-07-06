import { useUser } from "./userContext";

const Header = () => {
  const { user } = useUser();
  return (
    <header>
      <h1>Hello {user.name}</h1>
    </header>
  );
};

export default Header;
