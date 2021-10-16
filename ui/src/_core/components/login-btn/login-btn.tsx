import React from 'react';
import { Button } from '../button';

export type LoginHandler = () => void;
export type LogoutHandler = () => void;

export interface LoginButtonProps {
  /** already authenticated */
  isAuthenticated: boolean;

  /** Login click handler */
  onLogin: LoginHandler;

  /** Logout click handler */
  onLogout: LogoutHandler;
}

export const LoginButton: React.FC<LoginButtonProps> = ({
  isAuthenticated,
  onLogin,
  onLogout,
}) => {
  return isAuthenticated ? (
    <Button type="button" label="Logout" onClick={onLogout} />
  ) : (
    <Button type="button" primary label="Login" onClick={onLogin} />
  );
};
