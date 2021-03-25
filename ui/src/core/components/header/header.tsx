import React from 'react';

import './header.scss';
import { Navigation, NavItem } from '../navigation';

export interface HeaderProps {
  navItems?: NavItem[];
}

export const Header: React.FC<HeaderProps> = ({ navItems = [] }) => (
  <header>
    <h1 className="title">My Library</h1>

    <Navigation navItems={navItems} />

    <section className="auth" />
  </header>
);
